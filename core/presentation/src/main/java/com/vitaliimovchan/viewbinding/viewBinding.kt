package com.vitaliimovchan.viewbinding

import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import java.lang.ref.WeakReference
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(crossinline inflate: (LayoutInflater) -> T) =
    lazy(LazyThreadSafetyMode.NONE) {
        inflate(layoutInflater)
    }

fun <T : ViewBinding> viewBinding(bind: (View) -> T) = FragmentViewBindingDelegate(bind)

class FragmentViewBindingDelegate<T : ViewBinding>(private val bind: (View) -> T) : ReadOnlyProperty<Fragment, T> {

    private var reference: WeakReference<T>? = null

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        val binding = reference?.get()
        return when {
            binding != null -> binding
            else -> bind(thisRef.requireView()).also { reference = WeakReference(it) }
        }
    }
}
