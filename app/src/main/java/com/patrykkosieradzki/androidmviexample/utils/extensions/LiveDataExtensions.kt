package com.patrykkosieradzki.androidmviexample.utils.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hadilq.liveevent.LiveEvent

fun <T> MutableLiveData<T>.fireChange(t: T) {
    value = t
}

inline val <T> MutableLiveData<T>.readOnly: LiveData<T>
    get() = this

inline val <T> LiveData<T>.valueNN
    get() = this.value!!

fun <T> LiveEvent<T>.fireEvent(event: T) {
    this.value = event
}

fun LiveEvent<Unit>.fireEvent() {
    this.value = Unit
}