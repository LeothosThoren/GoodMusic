package com.leothos.goodmusic.data.network.di

import android.content.Context
import coil.ImageLoader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun imageLoader(
        @ApplicationContext application: Context,
    ): ImageLoader = ImageLoader.Builder(application)
        .crossfade(true)
        .build()
}