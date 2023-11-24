package com.leothos.goodmusic.data.network.di


import com.leothos.goodmusic.data.network.GoodMusicNetworkDatasource
import com.leothos.goodmusic.data.network.RetrofitGoodMusicNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NetworkDataSourceModule {

    @Binds
    fun bindsGoodMusicNetworkDatasource(retrofitGoodMusicNetwork: RetrofitGoodMusicNetwork): GoodMusicNetworkDatasource
}