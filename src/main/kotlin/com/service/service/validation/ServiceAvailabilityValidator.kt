package com.service.service.validation

import com.service.service.util.ValidationResultWrapper
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
        throw NotImplementedError()
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

    private fun validateUserId(userId: String) {
        throw NotImplementedError()
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
