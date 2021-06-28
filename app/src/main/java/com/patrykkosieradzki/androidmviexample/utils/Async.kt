package com.patrykkosieradzki.androidmviexample.utils

sealed class Async<out T>(val complete: Boolean, val shouldLoad: Boolean, private val value: T?) {
    open operator fun invoke(): T? = value

    companion object {
        fun <T> Success<*>.setMetadata(metadata: T) {
            this.metadata = metadata
        }

        fun <T> Success<*>.getMetadata(): T? = this.metadata as T?
    }
}

object Uninitialized : Async<Nothing>(complete = false, shouldLoad = true, value = null), Incomplete

data class Loading<out T>(private val value: T? = null) :
    Async<T>(complete = false, shouldLoad = false, value = value), Incomplete

data class Success<out T>(private val value: T) :
    Async<T>(complete = true, shouldLoad = false, value = value) {
    override operator fun invoke(): T = value

    var metadata: Any? = null
}

data class Fail<out T>(val error: Throwable, private val value: T? = null) :
    Async<T>(complete = true, shouldLoad = true, value = value) {
    override fun equals(other: Any?): Boolean {
        if (other !is Fail<*>) return false

        val otherError = other.error
        return error::class == otherError::class &&
                error.message == otherError.message &&
                error.stackTrace.firstOrNull() == otherError.stackTrace.firstOrNull()
    }

    override fun hashCode(): Int =
        arrayOf(error::class, error.message, error.stackTrace.firstOrNull()).contentHashCode()
}

interface Incomplete
