package com.leothos.goodmusic.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.leothos.goodmusic.data.database.dao.SongDao
import com.leothos.goodmusic.data.database.entity.SongEntity

@Database(
    entities = [SongEntity::class],
    version = 1
)
abstract class GoodMusicDatabase : RoomDatabase() {

    abstract fun songDao(): SongDao
}