package com.omertex.test.app.data.datasource.api

import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApi {
    @GET("photos")
    suspend fun getListPhotos(
        @Query("per_page") pageSize: Int
    )
}