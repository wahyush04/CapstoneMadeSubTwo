package com.wahyush04.core.data.source.local

import com.wahyush04.core.data.source.local.entity.GnewsEntity
import com.wahyush04.core.data.source.local.room.GnewsDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val gnewsDao: GnewsDao) {
    fun getList(): Flow<List<GnewsEntity>> = gnewsDao.getListNews()
    fun getBookmarkNews(): Flow<List<GnewsEntity>> = gnewsDao.getBookmarkNews()
    suspend fun insertNews(newsList: List<GnewsEntity>) = gnewsDao.insertNews(newsList)
    fun setBookmarkNews(news: GnewsEntity, newState: Boolean){
        news.isBookmark = newState
        gnewsDao.updateBookmarkNews(news)
    }
}