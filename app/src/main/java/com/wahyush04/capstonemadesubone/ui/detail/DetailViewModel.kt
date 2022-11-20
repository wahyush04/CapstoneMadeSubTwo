package com.wahyush04.capstonemadesubone.ui.detail

import androidx.lifecycle.ViewModel
import com.wahyush04.core.domain.model.News
import com.wahyush04.core.domain.usecase.NewsUseCase

class DetailViewModel(private val newsUseCase: NewsUseCase) : ViewModel() {

    fun bookmark(news: News, newState: Boolean) = newsUseCase.setBookmarkNews(news, newState)

}