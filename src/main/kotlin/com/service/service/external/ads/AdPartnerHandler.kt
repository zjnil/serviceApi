package com.service.service.external.ads

import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import mu.KotlinLogging
import java.util.*

// TODO: Add tests
class AdPartnerHandler(private val url: String, username: String, password: String) {
    private val basicAuth: String
    private val logger = KotlinLogging.logger {}

    init {
        val basicAuthStr = "${username}:${password}"
        this.basicAuth = Base64.getEncoder().encodeToString(basicAuthStr.toByteArray())
    }

    fun areAdsEnabled(countryCode: String): Boolean {
        val url = "${this.url}/fun7-ad-partner"
        val query = listOf("countryCode" to countryCode)
        val (_, response, result) = url
            .httpGet(query)
            .responseObject(ExternalAdStatusDeserializer())

        when (result) {
            is Result.Failure<*> -> {
                if (response.statusCode == 400) {
                    this.logger.error {"Bad request when getting external AD status"}
                    throw result.getException()
                } else if (response.statusCode == 500) {
                    return false
                }

                this.logger.error {"Unexpected API failure"}
                throw result.getException()
            }
            is Result.Success<*> -> {
                return this.areResponseAdsEnabled(result.get())
            }
        }
    }

    private fun areResponseAdsEnabled(responseData: ExternalAdStatus): Boolean {
        return responseData.ads === "sure, why not!"
    }
}
