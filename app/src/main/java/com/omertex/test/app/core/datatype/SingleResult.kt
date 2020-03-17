package com.omertex.test.app.core.datatype

sealed class SingleResult<out R> {
    data class Success<out T>(val data: T) : SingleResult<T>()
    data class Error(
        val exception: Exception,
        val exceptionMessage: String = ""
    ) : SingleResult<Nothing>()
    data class Canceled(val exception: Exception?) : SingleResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception], ErrorMessage = $exceptionMessage"
            is Canceled -> "Canceled[exception=$exception]"
        }
    }

    suspend fun <T> flatMap(block: suspend R.() -> SingleResult<T>): SingleResult<T> {
        return when (this) {
            is Success<R> -> {
                block(this.data)
            }
            is Error -> {
                Error(
                    this.exception,
                    this.exceptionMessage
                )
            }
            is Canceled -> {
                Canceled(this.exception)
            }
        }
    }

    fun <T> map(transform: (value: R) -> T): SingleResult<T> {
        return when (this) {
            is Success<R> -> {
                return try {
                    Success(
                        transform(this.data)
                    )
                } catch (exception: Exception) {
                    Error(
                        exception,
                        "Can't transform object."
                    )
                }
            }
            is Error -> {
                Error(
                    this.exception,
                    this.exceptionMessage
                )
            }
            is Canceled -> {
                Canceled(this.exception)
            }
        }
    }

    fun onSuccess(block: (value: R) -> Unit): SingleResult<R> {
        if (this is Success<R>) {
            block(this.data)
        }
        return this
    }

    fun onError(block: (value: Exception) -> Unit): SingleResult<R> {
        if (this is Error) {
            block(this.exception)
        }
        return this
    }

    fun onCanceled(block: (value: Exception?) -> Unit): SingleResult<R> {
        if (this is Canceled) {
            block(this.exception)
        }
        return this
    }
}