package com.omertex.test.app.data.datasource.api

import com.omertex.test.app.data.model.placeholder.User
import retrofit2.http.GET

interface PlaceHolderFakeApi {
    @GET("users")
    suspend fun getUserList() : List<User>
}