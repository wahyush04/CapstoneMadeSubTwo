package com.wahyush04.core.domain.usecase

import com.wahyush04.core.domain.model.News
import com.wahyush04.core.domain.repository.INewsRepository

class NewsInteractor(private val newsRepository: INewsRepository): NewsUseCase {
    override fun getAllNews() = newsRepository.getAllList()

    override fun getBookmarkNews() = newsRepository.getBookmarkList()

    override fun setBookmarkNews(news: News, state: Boolean) = newsRepository.setBookmarkList(news, state)
}