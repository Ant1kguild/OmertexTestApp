package com.omertex.test.app.presentation.ui.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations

sealed class ViewModelState<out R> {
    object Loading : ViewModelState<Nothing>()
    data class Success<out T>(val data: T) : ViewModelState<T>()
    object Failed : ViewModelState<Nothing>()
    object Idle : ViewModelState<Nothing>()

    fun onSuccess(block: (R) -> Unit): ViewModelState<R> {
        if (this is Success) {
            block(this.data)
        }
        return this
    }

    fun onFailed(block: () -> Unit): ViewModelState<R> {
        if (this is Failed) {
            block()
        }
        return this
    }

    fun onLoading(block: () -> Unit): ViewModelState<R> {
        if (this is Loading) {
            block()
        }
        return this
    }

    companion object {
        fun <T> success(value: T): ViewModelState<T> {
            return Success(value)
        }

        fun <T> failed(): ViewModelState<T> {
            return Failed
        }

        fun <T> loading(): ViewModelState<T> {
            return Loading
        }

        fun <T> idle(): ViewModelState<T> {
            return Idle
        }
    }
}


inline fun <X, Y> LiveData<ViewModelState<X>>.mapViewModel(crossinline transform: (X) -> Y): LiveData<ViewModelState<Y>> =
    Transformations.map(this) {
        when (it) {
            is ViewModelState.Success -> ViewModelState.success(transform(it.data))
            is ViewModelState.Loading -> ViewModelState.loading()
            is ViewModelState.Failed -> ViewModelState.failed()
            is ViewModelState.Idle -> ViewModelState.idle()
        }
    }