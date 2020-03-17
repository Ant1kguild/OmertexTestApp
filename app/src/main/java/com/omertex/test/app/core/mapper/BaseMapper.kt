package com.omertex.test.app.core.mapper

interface BaseMapper<in A : Any, out B : Any> {
    fun map(from: A): B
}