package com.omertex.test.app.domain

import com.omertex.test.app.data.model.placeholder.User
import com.omertex.test.app.data.model.unsplash.Photo
import kotlinx.coroutines.flow.Flow

interface OxTestRepository {
    suspend fun getUsers() : List<User>

    suspend fun getPhoto(numberOfPhotos : Int) : List<Photo>
}