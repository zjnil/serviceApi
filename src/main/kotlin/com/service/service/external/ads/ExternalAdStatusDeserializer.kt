package com.service.service.external.ads

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.github.kittinunf.fuel.core.ResponseDeserializable

class ExternalAdStatusDeserializer: ResponseDeserializable<ExternalAdStatus> {
    override fun deserialize(bytes: ByteArray) =
        jacksonObjectMapper().readValue<ExternalAdStatus>(bytes)
}
