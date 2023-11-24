package com.leothos.goodmusic.data.network

import com.leothos.goodmusic.data.network.dto.SongItemDto
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Streaming
import javax.inject.Inject
import javax.inject.Singleton


private const val BASE_URL = "https://static.leboncoin.fr/img/shared/"

interface SongServiceApi {
    @Streaming
    @GET("technical-test.json")
    suspend fun getSongs(): List<SongItemDto>
}

@Singleton
class RetrofitGoodMusicNetwork @Inject constructor(
    okHttpClient: OkHttpClient
) : GoodMusicNetworkDatasource {

    private val networkApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)
        .build()
        .create(SongServiceApi::class.java)

    override suspend fun getSongs(): List<SongItemDto> =
        networkApi.getSongs()

}


