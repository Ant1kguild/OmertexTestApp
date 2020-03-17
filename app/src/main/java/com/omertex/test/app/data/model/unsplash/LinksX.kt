package com.omertex.test.app.data.model.unsplash

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class LinksX(
    val html: String,
    val likes: String,
    val photos: String,
    val portfolio: String,
    val self: String
)