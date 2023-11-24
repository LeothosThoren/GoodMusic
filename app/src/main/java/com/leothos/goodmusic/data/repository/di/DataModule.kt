package com.leothos.goodmusic.data.repository.di

import com.leothos.goodmusic.data.repository.SongRepository
import com.leothos.goodmusic.data.repository.SongRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsSongRepository(songRepository: SongRepositoryImpl): SongRepository
}