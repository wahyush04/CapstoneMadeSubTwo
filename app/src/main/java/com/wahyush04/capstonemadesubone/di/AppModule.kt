package com.wahyush04.capstonemadesubone.di

import com.wahyush04.capstonemadesubone.ui.detail.DetailViewModel
import com.wahyush04.capstonemadesubone.ui.main.MainViewModel
import com.wahyush04.core.domain.usecase.NewsInteractor
import com.wahyush04.core.domain.usecase.NewsUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<NewsUseCase>{
        NewsInteractor(get())
    }
}

val viewModelModule = module {
    viewModel {
        MainViewModel(get())
    }
    viewModel {
        DetailViewModel(get())
    }
}