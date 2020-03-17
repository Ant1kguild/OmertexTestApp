package com.omertex.test.app.data.di

import com.omertex.test.app.data.datasource.PlaceHolderDataSource
import com.omertex.test.app.data.datasource.UnsplashDataSource
import com.omertex.test.app.data.repository.OxTestRepositoryImpl
import com.omertex.test.app.domain.OxTestRepository
import com.omertex.test.app.presentation.InjectPH
import com.omertex.test.app.presentation.InjectUnsplash
import org.koin.dsl.module


val apiModule = module {
    factory { InjectPH.providePlaceHolderApi() }
    factory { InjectUnsplash.provideUnsplashApi() }
    single { PlaceHolderDataSource(api = get()) }
    single { UnsplashDataSource(api = get()) }
}

val repositoryModule = module {
    single<OxTestRepository> {
        OxTestRepositoryImpl(
            placeHolderDataSource = get(),
            unsplashDataSource = get()
        )
    }
}

val useCaseModule = module {

}