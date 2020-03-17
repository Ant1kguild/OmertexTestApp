package com.omertex.test.app.domain

import com.omertex.test.app.core.datatype.SingleResult
import com.omertex.test.app.data.model.placeholder.User
import com.omertex.test.app.data.model.unsplash.Photo
import kotlinx.coroutines.flow.Flow

interface OxTestRepository {
    suspend fun getUsers() : SingleResult<List<User>>

    suspend fun getPhoto(numberOfPhotos : Int) : SingleResult<List<Photo>>
}