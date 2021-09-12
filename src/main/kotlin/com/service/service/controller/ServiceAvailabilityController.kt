package com.service.service.controller

import com.service.service.provider.ServiceAvailabilityProvider
import com.service.service.validation.ServiceAvailabilityValidator
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("service")
class ServiceAvailabilityController() {
    @GetMapping("/")
    fun getServiceAvailability(
        @RequestParam timezone: String,
        @RequestParam userId: String,
        @RequestParam cc: String
    ): ResponseEntity<String> {
        val validator = ServiceAvailabilityValidator()
        val valRes = validator.validate(timezone, userId, cc)
        if (valRes.hasErrors()) {
            return ResponseEntity(valRes.errors.toString(), HttpStatus.BAD_REQUEST)
        }

        val result = valRes.validationResult
        val sap = ServiceAvailabilityProvider()
        val availability = sap.getAvailability(
            result.user.visitCount,
            result.locale,
            "https://us-central1-o7tools.cloudfunctions.net",
            "fun7user",
            "fun7pass"
        )

        val util = ServiceAvailabilityUtil()
        return ResponseEntity(util.availabilityToStr(availability), HttpStatus.OK)
    }
}
