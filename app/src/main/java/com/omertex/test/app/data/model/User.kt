package com.omertex.test.app.data.model

import com.omertex.test.app.data.model.Address
import com.omertex.test.app.data.model.Company

data class User(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)