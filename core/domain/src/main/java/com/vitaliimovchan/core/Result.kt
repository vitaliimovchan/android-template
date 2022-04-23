package com.vitaliimovchan.core

@Suppress("MemberVisibilityCanBePrivate")
class Result<T> private constructor(private val value: Any?) {
    val isFailure get() = value is Error
    val isSuccess get() = !isFailure

    @Suppress("UNCHECKED_CAST")
    fun onSuccess(block: (T) -> Unit) = apply {
        if (isSuccess) block(value as T)
    }

    fun onFailure(block: (Error) -> Unit) = apply {
        if (isFailure) block(value as Error)
    }

    fun onAny(block: () -> Unit) = apply {
        block()
    }

    companion object {
        fun <T> success(value: T) = Result<T>(value)
        fun <T> failure(error: Error) = Result<T>(error)
    }
}
