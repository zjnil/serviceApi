package com.service.service.validation

import com.service.service.model.User
import java.util.*

data class ServiceAvailabilityValidationResult(val timeZone: TimeZone, val user: User, val locale: Locale)
