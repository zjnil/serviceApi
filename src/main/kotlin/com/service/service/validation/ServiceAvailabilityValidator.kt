package com.service.service.validation

import com.service.service.DatabaseSingleton
import com.service.service.model.User
import com.service.service.model.UserTable
import com.service.service.util.ValidationResultWrapper
import org.ktorm.dsl.asIterable
import org.ktorm.dsl.from
import org.ktorm.dsl.limit
import org.ktorm.dsl.select
import java.util.*

class ServiceAvailabilityValidator {
    /**
     * Validate service availability check input.
     * @param timeZone 2-letter time zone
     * @param userId User GUID
     * @param countryCode 2-letter country code
     */
    fun validate(
        timeZone: String,
        userId: String,
        countryCode: String
    ): ValidationResultWrapper<ServiceAvailabilityValidationResult> {
        val vrw = ValidationResultWrapper<ServiceAvailabilityValidationResult>()
        val timeZoneVrw = this.validateTimeZone(timeZone)
        vrw.absorb(timeZoneVrw)
        val userIdVrw = this.validateUserId(userId)
        vrw.absorb(userIdVrw)
        val ccVrw = this.validateCountryCode(countryCode)
        vrw.absorb(ccVrw)
        return vrw
    }

    private fun validateTimeZone(timeZone: String): ValidationResultWrapper<TimeZone> {
        val vrw = ValidationResultWrapper<TimeZone>()
        val allTimeZones = TimeZone.getAvailableIDs()
        if (!allTimeZones.contains(timeZone)) {
            vrw.addError("Provided time zone is not supported: $timeZone")
            return vrw
        }

        vrw.validationResult = TimeZone.getTimeZone(timeZone)
        return vrw
    }

    private fun validateUserId(userId: String): ValidationResultWrapper<User> {
        val vrw = ValidationResultWrapper<User>()
        val database = DatabaseSingleton().database
        val user = database.from(UserTable).select().limit(1).asIterable().firstOrNull() as User?
        if (user === null) {
            vrw.addError("Provided user ID does not exist: $userId")
            return vrw
        }

        vrw.validationResult = user
        return vrw
    }

    private fun validateCountryCode(countryCode: String): ValidationResultWrapper<Locale> {
        val vrw = ValidationResultWrapper<Locale>()
        val allCountries = Locale.getISOCountries()
        if (!allCountries.contains(countryCode)) {
            vrw.addError("Provided country code is not supported: $countryCode")
            return vrw
        }

        vrw.validationResult = Locale("", countryCode)
        return vrw
    }
}
