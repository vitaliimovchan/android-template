package com.vitaliimovchan.lifecycle

data class Event<out T>(val content: T) {
    private var consumed = false

    fun consume(consumer: (T) -> Unit) {
        if (consumed.not()) {
            consumed = true
            consumer(content)
        }
    }
}
