package com.wahyush04.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_news")
data class GnewsEntity (


    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "content")
    val content: String,

    @ColumnInfo(name = "url")
    val url: String,

    @ColumnInfo(name = "image")
    val image: String,

    @PrimaryKey
    @ColumnInfo(name = "publishedAt")
    val publishedAt: String,

    @ColumnInfo(name = "isBookmark")
    var isBookmark: Boolean = false

)