package com.vitaliimovchan.domain

import kotlinx.coroutines.CoroutineDispatcher

interface UseCaseScope {

    val dispatcher: CoroutineDispatcher

    fun logException(exception: Throwable, msg: String)

    fun mapError(exception: Throwable): Error
}
