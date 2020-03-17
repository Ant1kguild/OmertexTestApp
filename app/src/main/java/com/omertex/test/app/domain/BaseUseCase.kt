package com.omertex.test.app.domain

import com.omertex.test.app.core.datatype.SingleResult


interface BaseUseCase<out T> {
    suspend fun execute(): SingleResult<T>
}
