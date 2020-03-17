package com.omertex.test.app.data.repository

import com.omertex.test.app.core.datatype.SingleResult
import com.omertex.test.app.data.datasource.PlaceHolderDataSource
import com.omertex.test.app.data.datasource.UnsplashDataSource
import com.omertex.test.app.data.model.placeholder.User
import com.omertex.test.app.data.model.unsplash.Photo
import com.omertex.test.app.domain.OxTestRepository

class OxTestRepositoryImpl(
    private val placeHolderDataSource: PlaceHolderDataSource,
    private val unsplashDataSource: UnsplashDataSource
) : OxTestRepository {

    override suspend fun getUsers(): SingleResult<List<User>> {
        return placeHolderDataSource.users()
    }

    override suspend fun getPhoto(numberOfPhotos: Int): SingleResult<List<Photo>> {
        return unsplashDataSource.photos(numberOfPhotos)
    }
}