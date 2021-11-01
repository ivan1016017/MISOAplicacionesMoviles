package com.example.vynilos.apis

import com.example.vynilos.models.Album
import com.example.vynilos.models.Artist
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url


interface ApiService {
    @GET
    suspend fun getAlbums(@Url url:String):Response<List<Album>>

    @GET
    suspend fun getArtists(@Url url:String):Response<List<Artist>>
}