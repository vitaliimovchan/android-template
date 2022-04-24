package com.vitaliimovchan.lifecycle

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

fun <T> LifecycleOwner.observe(
    liveData: EventLiveData<T>,
    block: (T) -> Unit
) = liveData.observe(this) { it.consume(block) }

fun <T : Any> Fragment.observe(
    liveData: EventLiveData<T>,
    block: (T) -> Unit
) = liveData.observe(viewLifecycleOwner) { it.consume(block) }

fun <T> LifecycleOwner.observe(
    liveData: LiveData<T>,
    block: (T) -> Unit
) = liveData.observe(this) { block(requireNotNull(it)) }

fun <T> Fragment.observe(
    liveData: LiveData<T>,
    block: (T) -> Unit
) = liveData.observe(viewLifecycleOwner) { block(requireNotNull(it)) }
