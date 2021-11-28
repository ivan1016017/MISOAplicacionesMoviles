package com.example.vynilos.network

import com.example.vynilos.models.Artist
import com.example.vynilos.models.Album

import com.example.vynilos.models.Collector
import com.example.vynilos.models.Track
import com.google.gson.JsonObject

import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET
    fun getAlbums(@Url url:String):Call<List<Album>>

    @GET
    fun getArtists(@Url url:String):Call<List<Artist>>

    @GET
    fun getCollectors(@Url url:String):Call<List<Collector>>

    @GET
    fun getAlbum(@Url url:String):Call<Album>

    @GET
    fun getArtist(@Url url:String):Call<Artist>

    @GET
    fun getCollector(@Url url:String):Call<Collector>

    @POST
    fun createTrackToAlbum(@Url url:String, @Body track: Track): Call<Track>

    @POST
    fun createAlbum(@Url url:String, @Body album: JsonObject): Call<Album>
}
