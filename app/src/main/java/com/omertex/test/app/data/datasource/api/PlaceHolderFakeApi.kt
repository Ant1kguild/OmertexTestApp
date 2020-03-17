package com.omertex.test.app.data.datasource.api

import com.omertex.test.app.data.model.placeholder.User
import retrofit2.http.GET
import retrofit2.http.Headers

interface PlaceHolderFakeApi {
    @Headers("Content-Type: application/json")
    @GET("users")
    suspend fun getUserList() : List<User>
}