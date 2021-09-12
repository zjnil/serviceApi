package com.service.service.util

import org.springframework.expression.AccessException

// TODO: Add tests
class ValidationResultWrapper<T>() {
    private var validationResultVal: T? = null
    private val errors = mutableListOf<String>()

    fun addError(error: String) {
        this.errors.add(error)
    }

    var validationResult: T
        get() {
            if (this.errors.size > 0) {
                throw AccessException("Access to the result is not possible in this state")
            }

            return this.validationResultVal ?: throw AccessException("Validation result is not set")
        }
        set(validationResult) {
            this.validationResultVal = validationResult
        }

    fun absorbErrors(vrw: ValidationResultWrapper<Any>) {
        this.errors.addAll(vrw.errors)
    }
}
