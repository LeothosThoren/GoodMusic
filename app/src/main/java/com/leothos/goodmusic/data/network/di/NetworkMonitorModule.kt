package com.leothos.goodmusic.data.network.di

import com.leothos.goodmusic.data.util.ConnectivityManagerNetworkMonitor
import com.leothos.goodmusic.data.util.NetworkMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NetworkMonitorModule {

    @Binds
    fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor,
    ): NetworkMonitor
}