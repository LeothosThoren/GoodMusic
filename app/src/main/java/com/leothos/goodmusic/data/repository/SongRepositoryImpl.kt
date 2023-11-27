package com.leothos.goodmusic.data.repository

import com.leothos.goodmusic.data.database.dao.SongDao
import com.leothos.goodmusic.data.database.entity.SongEntity
import com.leothos.goodmusic.data.mapper.toSongEntity
import com.leothos.goodmusic.data.network.GoodMusicNetworkDatasource
import com.leothos.goodmusic.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SongRepositoryImpl @Inject constructor(
    private val goodMusicNetworkDatasource: GoodMusicNetworkDatasource,
    private val songDao: SongDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : SongRepository {

    override suspend fun insertOrUpdateSongs() {
        withContext(ioDispatcher) {
            songDao.upsertSongEntities(
                goodMusicNetworkDatasource.getSongs()
                    .map {
                        it.toSongEntity()
                    })
        }
    }

    override suspend fun markSongAsFavorite(id: Int, isFavorite: Boolean) {
        songDao.setOrUnsetSongEntityToFavorite(id, isFavorite)
    }

    override fun getSongs(): Flow<List<SongEntity>> {
        return songDao.getSongEntities()
    }

    override fun getSongsFromAlbum(albumId: Int): Flow<List<SongEntity>> {
        return songDao.getSongEntitiesFromAlbumId(albumId)
    }

    override fun getFavoriteSongs(): Flow<List<SongEntity>> {
        return songDao.getFavoriteSongEntities()
    }

    override suspend fun clearAll() {
        songDao.clearSongEntities()
    }
}