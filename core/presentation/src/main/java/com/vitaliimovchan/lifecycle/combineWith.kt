package com.vitaliimovchan.lifecycle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

fun <T, L, M> LiveData<T>.combineWith(
    liveData: LiveData<L>,
    block: (T, L) -> M
): LiveData<M> {

    val mediator = MediatorLiveData<M>()

    fun tryEmit(t: T? = value, l: L? = liveData.value) {
        if (t != null && l != null) {
            val new = block(t, l)
            if (new != mediator.value) mediator.value = new
        }
    }

    mediator.addSource(this) {
        tryEmit(t = it)
    }
    mediator.addSource(liveData) {
        tryEmit(l = it)
    }

    return mediator
}
