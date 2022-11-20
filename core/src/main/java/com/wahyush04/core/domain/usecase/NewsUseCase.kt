package com.wahyush04.core.domain.usecase

import com.wahyush04.core.data.Resource
import com.wahyush04.core.domain.model.News
import kotlinx.coroutines.flow.Flow

interface NewsUseCase {
    fun getAllNews(): Flow<Resource<List<News>>>
    fun getBookmarkNews(): Flow<List<News>>
    fun setBookmarkNews(news: News, state: Boolean)
}