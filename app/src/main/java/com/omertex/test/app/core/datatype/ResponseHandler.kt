package com.omertex.test.app.core.datatype

import okhttp3.internal.http2.ErrorCode
import retrofit2.HttpException
import java.net.SocketTimeoutException

object ResponseHandler {

    fun <T : Any> handleSuccess(data: T): SingleResult<T> {
        return SingleResult.Success(data)
    }

    fun <T : Any> handleException(exception: Exception): SingleResult<T> {
        return when (exception) {
            is HttpException -> SingleResult.Error(exception, getErrorMessage(exception.code()))
            is SocketTimeoutException -> SingleResult.Error(
                exception,
                getErrorMessage(ErrorCode.SETTINGS_TIMEOUT.httpCode)
            )
            else -> SingleResult.Error(exception)
        }
    }

    private fun getErrorMessage(code: Int): String {
        return when (code) {
            ErrorCode.SETTINGS_TIMEOUT.httpCode -> "Time out"
            401 -> "Unauthorised"
            402 -> "Payment Required"
            404 -> "Not found"
            408 -> "Time out"
            else -> "Something went wrong"
        }
    }
}