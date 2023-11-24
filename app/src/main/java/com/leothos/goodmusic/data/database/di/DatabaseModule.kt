package com.leothos.goodmusic.data.database.di

import android.content.Context
import androidx.room.Room
import com.leothos.goodmusic.data.database.GoodMusicDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesGoodMusicDatabase(
        @ApplicationContext context: Context,
    ): GoodMusicDatabase = Room.databaseBuilder(
        context,
        GoodMusicDatabase::class.java,
        "music-database",
    ).build()
}
