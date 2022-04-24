package com.vitaliimovchan.lifecycle

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

suspend fun <T> Flow<T>.collect(liveData: MutableLiveData<T>) {
    collect { liveData.value = it }
}
