package com.service.service.controller

import com.service.service.provider.ServiceAvailability

class ServiceAvailabilityUtil {
    /**
     * Transforms service availability to JSON string.
     * @param availability Service availability
     */
    fun availabilityToStr(availability: ServiceAvailability): String {
        val multiplayerStr = this.getStatus(availability.isMultiplayerEnabled)
        val userSupportStr = this.getStatus(availability.isUserSupportEnabled)
        val adsStr = this.getStatus(availability.areAdsEnabled)

        return """{
            |multiplayer: $multiplayerStr,
            |user-support: $userSupportStr,
            |ads: $adsStr
        |}""".trimMargin()
    }

    private fun getStatus(status: Boolean): String = if (status) "Enabled" else "Disabled"
}
