package com.wahyush04.bookmarks.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.wahyush04.core.domain.usecase.NewsUseCase

class BookmarkViewModel(newsUseCase: NewsUseCase) : ViewModel() {
    val newsBookmarkList = newsUseCase.getBookmarkNews().asLiveData()
}