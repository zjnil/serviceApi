package com.service.service.calculator

import java.time.Instant
import java.util.*

class ServiceCalculator {
    companion object {
        /**
         * Check if multiplayer is enabled.
         * @param userVisitCount Number of times the user has visited the website
         * @param userCC 2-letter code of the user country
         * @param multiplayerCC 2-letter country code where multiplayer is enabled
         */
        fun isMultiplayerEnabled(userVisitCount: Int, userCC: String, multiplayerCC: String): Boolean {
            return userVisitCount > 5 && userCC === multiplayerCC
        }

        /**
         * Check if customer support is enabled.
         * @param time Current time in UTC
         * @param customerSupportTz Time zone of the country where customer support is located
         */
        fun isUserSupportEnabled(time: Instant, customerSupportTz: TimeZone): Boolean {
            val localTime = time.atZone(customerSupportTz.toZoneId())
            return localTime.hour in 9..14
        }
    }
}