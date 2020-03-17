package com.omertex.test.app.data.model

import com.omertex.test.app.data.model.placeholder.Address
import com.omertex.test.app.data.model.placeholder.Company
import com.omertex.test.app.data.model.unsplash.Urls

data class MergeModel(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String,
    val urls: Urls?
)