package com.omertex.test.app.data.datasource

import com.omertex.test.app.core.datatype.ResponseHandler
import com.omertex.test.app.core.datatype.SingleResult
import com.omertex.test.app.data.datasource.api.PlaceHolderFakeApi
import com.omertex.test.app.data.model.placeholder.User

class PlaceHolderDataSource(private val api: PlaceHolderFakeApi) {
    suspend fun users() : SingleResult<List<User>>{
        return try {
            val result = api.getUserList()
            ResponseHandler.handleSuccess(result)
        } catch (e: Exception){
            ResponseHandler.handleException(e)
        }
    }
}