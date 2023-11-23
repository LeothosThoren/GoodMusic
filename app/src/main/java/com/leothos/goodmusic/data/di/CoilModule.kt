package com.leothos.goodmusic.data.di

import android.content.Context
import coil.ImageLoader
import com.leothos.goodmusic.R
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
        .placeholder(R.drawable.ic_launcher_foreground)
        .build()
}