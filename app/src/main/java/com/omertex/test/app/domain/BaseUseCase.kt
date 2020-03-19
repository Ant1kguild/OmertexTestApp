package com.omertex.test.app.domain

import kotlinx.coroutines.flow.Flow


interface BaseUseCase<out T> {
    suspend fun execute(): Flow<T>
}
