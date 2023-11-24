package com.leothos.goodmusic.data.repository

import com.leothos.goodmusic.data.mapper.toSong
import com.leothos.goodmusic.data.network.GoodMusicNetworkDatasource
import com.leothos.goodmusic.di.IoDispatcher
import com.leothos.goodmusic.model.Song
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SongRepositoryImpl @Inject constructor(
    private val goodMusicNetworkDatasource: GoodMusicNetworkDatasource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : SongRepository {
    override suspend fun getSongs(): List<Song> {
        return withContext(ioDispatcher) {
            goodMusicNetworkDatasource.getSongs().map {
                it.toSong()
            }
        }
    }
}