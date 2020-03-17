package com.omertex.test.app.domain

import com.omertex.test.app.data.model.placeholder.User
import kotlinx.coroutines.flow.Flow

interface OxTestRepository {
    suspend fun getUsers() : Flow<List<User>>

    //suspend fun getRandomPhoto() : Flow<List<String>>
}