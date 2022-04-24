package com.vitaliimovchan.lifecycle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.job
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Creates coroutine scope with [SupervisorJob] where parent is [viewModelScope].
 *
 * A failure or cancellation of a parent cause the supervisor job to cancel.
 * A failure or cancellation of a child does not cause the parent to fail.
 */
fun childScope() = ViewModelChildScopeDelegate()

class ViewModelChildScopeDelegate : ReadOnlyProperty<ViewModel, CoroutineScope> {

    override fun getValue(thisRef: ViewModel, property: KProperty<*>): CoroutineScope {
        val parentJob = thisRef.viewModelScope.coroutineContext.job
        return CoroutineScope(SupervisorJob(parentJob) + Dispatchers.Main.immediate)
    }
}
