package com.service.service.controller

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
        // TODO: Add validation
        // TODO: Call ServiceAvailabilityProvider
        // TODO: Call external api provider
        // TODO: Call calculator
        // TODO: Call
    }
}
