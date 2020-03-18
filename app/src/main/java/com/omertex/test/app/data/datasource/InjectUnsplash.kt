package com.omertex.test.app.data.datasource

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.omertex.test.app.data.datasource.api.UnsplashApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object InjectUnsplash {
    private const val BASE_URL = "https://api.unsplash.com/"

    private fun loggerInterceptor(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor()
        logger.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logger
    }

    private fun provideClient(): OkHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(loggerInterceptor())
        .build()

    private fun provideRetrofitInstance(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(provideClient())
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    fun provideUnsplashApi(): UnsplashApi =
        provideRetrofitInstance()
            .create(UnsplashApi::class.java)
}