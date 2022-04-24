package com.vitaliimovchan

import kotlinx.coroutines.withContext

open class UseCase(
    private val scope: UseCaseScope
) {
    protected suspend fun <T : Any> wrapResult(
        block: suspend () -> T
    ) = withContext(scope.dispatcher) {
        try {
            Result.success(block())
        } catch (expected: Exception) {
            logException(expected)
            Result.failure(scope.mapError(expected))
        }
    }

    @Suppress("MemberVisibilityCanBePrivate")
    protected fun logException(throwable: Throwable, tag: String = "") {
        scope.logException(throwable, javaClass.simpleName + tag)
    }
}
