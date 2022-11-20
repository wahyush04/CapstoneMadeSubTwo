package com.wahyush04.core.data.source.local.room

import androidx.room.*
import com.wahyush04.core.data.source.local.entity.GnewsEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface GnewsDao {

    @Query("SELECT * FROM tbl_news")
    fun getListNews(): Flow<List<GnewsEntity>>

    @Query("SELECT * FROM tbl_news WHERE isBookmark = 1")
    fun getBookmarkNews(): Flow<List<GnewsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(news: List<GnewsEntity>)

    @Update
    fun updateBookmarkNews(news: GnewsEntity)
}