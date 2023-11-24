package com.leothos.goodmusic.data.network

import com.leothos.goodmusic.data.network.dto.SongItemDto

interface GoodMusicNetworkDatasource {
    suspend fun getSongs(): List<SongItemDto>
}