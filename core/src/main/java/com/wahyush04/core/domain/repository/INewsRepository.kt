package com.wahyush04.core.domain.repository

import com.wahyush04.core.data.Resource
import com.wahyush04.core.domain.model.News
import kotlinx.coroutines.flow.Flow

interface INewsRepository {

    fun getAllList(): Flow<Resource<List<News>>>

    fun getBookmarkList(): Flow<List<News>>

    fun setBookmarkList(news: News, state: Boolean)
}