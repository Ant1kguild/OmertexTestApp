package com.omertex.test.app.data.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val URL_BASE_FAKE = "https://jsonplaceholder.typicode.com/"

private fun loggerInterceptor(): HttpLoggingInterceptor {
    val logger = HttpLoggingInterceptor()
    logger.setLevel(HttpLoggingInterceptor.Level.BODY)
    return logger
}

private fun provideClient(): OkHttpClient = OkHttpClient()
    .newBuilder()
    .addInterceptor(loggerInterceptor())
    .build()

private fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl(URL_BASE_FAKE)
    .client(okHttpClient)
    .addConverterFactory(MoshiConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()

val retrofitModule = module {
    factory { provideClient() }
    single { provideRetrofitInstance(okHttpClient = get()) }
}