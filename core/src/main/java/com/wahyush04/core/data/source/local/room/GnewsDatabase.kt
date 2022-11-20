package com.wahyush04.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wahyush04.core.data.source.local.entity.GnewsEntity

@Database(
    entities = [GnewsEntity::class],
    version = 1,
    exportSchema = false
)

abstract class GnewsDatabase : RoomDatabase() {
    abstract fun gnewshDao() : GnewsDao
}