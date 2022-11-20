package com.wahyush04.capstonemadesubone.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.wahyush04.core.domain.usecase.NewsUseCase

class MainViewModel(newsUseCase: NewsUseCase): ViewModel() {

    val news = newsUseCase.getAllNews().asLiveData()
}