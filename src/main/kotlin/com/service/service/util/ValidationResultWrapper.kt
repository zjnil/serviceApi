package com.service.service.util

import jdk.nashorn.internal.ir.annotations.Ignore
import org.springframework.expression.AccessException

// TODO: Add tests
class ValidationResultWrapper<T>() {
    private var validationResultVal: T? = null
    private val errorsInt = mutableListOf<String>()

    var validationResult: T
        get() {
            if (this.errorsInt.size > 0) {
                throw AccessException("Access to the result is not possible in this state")
            }

            return this.validationResultVal ?: throw AccessException("Validation result is not set")
        }
        set(validationResult) {
            this.validationResultVal = validationResult
        }

    val errors: MutableList<String>
        get() {
            return this.errorsInt
        }

    /**
     * Add error to errors list.
     * @param error Error to add
     */
    fun addError(error: String) {
        this.errorsInt.add(error)
    }

    /**
     * Absorb problems from given ValidationResultWrapper.
     * @param vrw Another instance of ValidationResultWrapper
     */
    fun absorb(vrw: ValidationResultWrapper<*>) {
        this.errorsInt.addAll(vrw.errorsInt)
    }

    /**
     * Return true if errors are present.
     */
    fun hasErrors(): Boolean {
        return this.errorsInt.size > 0
    }
}
