package com.omertex.test.app.data.datasource

import com.omertex.test.app.core.datatype.ResponseHandler
import com.omertex.test.app.core.datatype.SingleResult
import com.omertex.test.app.data.datasource.api.UnsplashApi
import com.omertex.test.app.data.model.unsplash.Photo
import java.lang.Exception

class UnsplashDataSource(private val api: UnsplashApi) {
    suspend fun photos(): SingleResult<List<Photo>> {
        return try {
            val result = api.getListPhotos(pageSize = 10)
            ResponseHandler.handleSuccess(result)
        } catch (ex: Exception) {
            ResponseHandler.handleException(ex)
        }
    }
}