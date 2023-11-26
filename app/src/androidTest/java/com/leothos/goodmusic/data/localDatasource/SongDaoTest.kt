package com.leothos.goodmusic.data.localDatasource

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.leothos.goodmusic.data.database.GoodMusicDatabase
import com.leothos.goodmusic.data.database.dao.SongDao
import com.leothos.goodmusic.data.database.entity.SongEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class SongDaoTest {

    private lateinit var db: GoodMusicDatabase
    private lateinit var songDao: SongDao
    private val songEntities = listOf(
        testSongEntity(1, 1),
        testSongEntity(2, 1),
        testSongEntity(3, 2),
        testSongEntity(4, 2)
    )

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            GoodMusicDatabase::class.java,
        ).build()

        songDao = db.songDao()
    }

    @Test
    fun songDao_insert_or_update_songs_from_different_albums() = runTest {
        songDao.upsertSongEntities(songEntities)
        val songFromDb = songDao.getSongEntities()

        assertEquals(songEntities.size, songFromDb.first().size)
    }

    @Test
    fun songDao_set_song_entities_to_favorite() = runTest {
        songDao.upsertSongEntities(songEntities)
        val favoriteSongs = songDao.getFavoriteSongEntities()

        // There si no favorite yet
        assertTrue(favoriteSongs.first().isEmpty())

        songDao.setOrUnsetSongEntityToFavorite(1, true)

        assertTrue(favoriteSongs.first().isNotEmpty())
    }

    @Test
    fun songDao_set_album_to_favorite() = runTest {
        songDao.upsertSongEntities(songEntities)
        val favoriteSongs = songDao.getFavoriteSongEntities()

        // There si no favorite yet
        assertTrue(favoriteSongs.first().isEmpty())

        songDao.setOrUnsetSongsEntityToFavorite(2, true)

        assertTrue(favoriteSongs.first().isNotEmpty())
        assertTrue(favoriteSongs.first().size == 2)
    }

    @Test
    fun songDao_get_song_entities_from_album_id() = runTest {
        songDao.upsertSongEntities(songEntities)
        val fullEntities = songDao.getSongEntities()
        val entitiesByAlbumId = songDao.getSongEntitiesFromAlbumId(2)

        assertNotEquals(fullEntities.first().size, entitiesByAlbumId.first().size)
        assertTrue(entitiesByAlbumId.first().size == 2)
    }

    @Test
    fun songDao_delete_all_song_entities_from_database() = runTest {
        songDao.upsertSongEntities(songEntities)
        val songEntitiesFromDb = songDao.getSongEntities()

        assertTrue(songEntitiesFromDb.first().isNotEmpty())

        songDao.clearSongEntities()

        assertTrue(songEntitiesFromDb.first().isEmpty())
    }


    private fun testSongEntity(id: Int, albumId: Int, isFavorite: Boolean = false) =
        SongEntity(
            id = id,
            albumId = albumId,
            isFavorite = isFavorite,
            title = "",
            pictureUrl = "",
            thumbnailUrl = ""
        )
}
