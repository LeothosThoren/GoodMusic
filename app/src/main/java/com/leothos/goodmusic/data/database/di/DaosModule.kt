package com.leothos.goodmusic.data.database.di

import com.leothos.goodmusic.data.database.GoodMusicDatabase
import com.leothos.goodmusic.data.database.dao.SongDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun providesSongDao(
        database: GoodMusicDatabase,
    ): SongDao = database.songDao()

}
