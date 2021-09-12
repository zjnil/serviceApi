package com.service.service.provider

import com.service.service.calculator.ServiceCalculator
import com.service.service.external.ads.AdPartnerHandler
import java.time.Instant
import java.util.*

class ServiceAvailabilityProvider {
    /**
     * Get availability for multiplayer, user support and AD services.
     * @param userVisitCount Number of time the user has visited the app
     * @param locale User locale
     * @param externalAdUrl URL of external AD provider
     * @param externalAdUser Username of external AD provider
     * @param externalAdPass Password of external AD provider
     */
    fun getAvailability(
        userVisitCount: Int,
        locale: Locale,
        externalAdUrl: String,
        externalAdUser: String,
        externalAdPass: String
    ): ServiceAvailability {
        val adPartnerHandler = AdPartnerHandler(externalAdUrl, externalAdUser, externalAdPass)
        return ServiceAvailability(
            ServiceCalculator.isMultiplayerEnabled(userVisitCount, locale.country, Locale.US.country),
            ServiceCalculator.isUserSupportEnabled(Instant.now(), TimeZone.getTimeZone("CEST")),
            adPartnerHandler.areAdsEnabled(locale.country)
        )
    }
}
