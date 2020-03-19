package com.omertex.test.app.di

import com.omertex.test.app.data.datasource.PlaceHolderDataSource
import com.omertex.test.app.data.datasource.UnsplashDataSource
import com.omertex.test.app.data.repository.OxTestRepositoryImpl
import com.omertex.test.app.domain.OxTestRepository
import com.omertex.test.app.data.model.MergeModelMapper
import com.omertex.test.app.domain.GetMergeListUseCase
import com.omertex.test.app.presentation.ui.NavigationViewModel
import com.omertex.test.app.presentation.ui.fragment.merge.MergeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val apiModule = module {
    factory { InjectPH.providePlaceHolderApi() }
    factory { InjectUnsplash.provideUnsplashApi() }

}

val dataModule = module {
    single { PlaceHolderDataSource(api = get()) }
    single { UnsplashDataSource(api = get()) }
    single<OxTestRepository> {
        OxTestRepositoryImpl(
            placeHolderDataSource = get(),
            unsplashDataSource = get()
        )
    }
}

val domainModule = module {
    factory { GetMergeListUseCase(oxTestRepository = get(), mapper = get()) }
}

val presentationModule = module {
    factory { MergeModelMapper() }

    viewModel { NavigationViewModel() }
    viewModel { MergeViewModel(getMergeListUseCase = get()) }
}