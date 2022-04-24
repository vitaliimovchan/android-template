package com.vitaliimovchan.activity

import android.app.Activity
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun <T : Any?> extra(key: String) = ActivityExtraDelegate<T>(key)

class ActivityExtraDelegate<T>(private val key: String) : ReadOnlyProperty<Activity, T> {

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: Activity, property: KProperty<*>): T {
        val value = thisRef.intent.extras?.get(key)
        if (value == null && !property.returnType.isMarkedNullable) {
            error("Not null property '${property.name}' received null in ${thisRef.javaClass.canonicalName}")
        }
        return value as T
    }
}
