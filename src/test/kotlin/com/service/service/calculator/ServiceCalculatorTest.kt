package com.service.service.calculator

import org.apache.tomcat.jni.Local
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.Instant
import java.time.ZoneOffset
import java.util.*

internal class ServiceCalculatorTest {
    @Test
    fun isMultiplayerEnabled() {
        val mulEnabledCC = Locale.US.country
        assertEquals(false, ServiceCalculator.isMultiplayerEnabled(-1, mulEnabledCC, mulEnabledCC))
        assertEquals(false, ServiceCalculator.isMultiplayerEnabled(5, mulEnabledCC, mulEnabledCC))
        assertEquals(false, ServiceCalculator.isMultiplayerEnabled(
            6,
            Locale.CANADA.country,
            mulEnabledCC
        ))

        assertEquals(false, ServiceCalculator.isMultiplayerEnabled(
            6,
            mulEnabledCC,
            Locale.CANADA.country
        ))

        assertEquals(true, ServiceCalculator.isMultiplayerEnabled(6, mulEnabledCC, mulEnabledCC))
    }

    @Test
    fun isUserSupportEnabled() {
        val atNine = Instant.ofEpochMilli(0).atZone(ZoneOffset.UTC)
            .withHour(9)
            .toInstant()

        val beforeNine = Instant.ofEpochMilli(0).atZone(ZoneOffset.UTC)
            .withHour(1)
            .toInstant()

        val atThree = Instant.ofEpochMilli(0).atZone(ZoneOffset.UTC)
            .withHour(15)
            .toInstant()

        val afterThree = Instant.ofEpochMilli(0).atZone(ZoneOffset.UTC)
            .withHour(15)
            .withNano(1)
            .toInstant()

        val between = Instant.ofEpochMilli(0).atZone(ZoneOffset.UTC)
            .withHour(13)
            .toInstant()

        val utcTz = TimeZone.getTimeZone(ZoneOffset.UTC.id)

        assertEquals(false, ServiceCalculator.isUserSupportEnabled(beforeNine, utcTz))
        assertEquals(false, ServiceCalculator.isUserSupportEnabled(afterThree, utcTz))
        assertEquals(false, ServiceCalculator.isUserSupportEnabled(atThree, utcTz))

        assertEquals(true, ServiceCalculator.isUserSupportEnabled(atNine, utcTz))
        assertEquals(true, ServiceCalculator.isUserSupportEnabled(between, utcTz))
    }
}