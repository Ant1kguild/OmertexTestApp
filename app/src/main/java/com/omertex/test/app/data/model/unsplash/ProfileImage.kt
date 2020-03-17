package com.omertex.test.app.data.model.unsplash

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProfileImage(
    val large: String,
    val medium: String,
    val small: String
)