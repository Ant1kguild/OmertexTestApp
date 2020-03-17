package com.omertex.test.app.core.mapper

interface BaseMapper<in A : Any, out B : Any> {
    fun map(from: A): B
}

interface BaseMapperPair<in A : Any, in B : Any, out C : Any> {
    fun map(fromFirst: A, fromSecond : B): C
}