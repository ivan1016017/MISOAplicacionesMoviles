package com.example.vynilos.network

import com.example.vynilos.models.Artist
import com.example.vynilos.models.Album
import com.example.vynilos.models.DetailAlbum
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    fun getAlbums(@Url url:String):Call<List<Album>>

    @GET
    fun getArtists(@Url url:String):Call<List<Artist>>

    @GET
    fun getAlbumDetail(@Url url:String):Call<List<DetailAlbum>>


}
