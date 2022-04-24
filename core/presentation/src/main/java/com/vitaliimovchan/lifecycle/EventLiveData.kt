package com.vitaliimovchan.lifecycle

import androidx.lifecycle.LiveData

class EventLiveData<T> : LiveData<Event<T>>() {

    fun emit(value: T) = setValue(Event(value))
}
