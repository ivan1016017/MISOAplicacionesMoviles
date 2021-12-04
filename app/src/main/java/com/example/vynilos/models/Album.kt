package com.example.vynilos.models

import android.content.pm.SigningInfo
import java.text.SimpleDateFormat
import com.example.vynilos.models.Track
import com.example.vynilos.repositories.AlbumRepository
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import org.json.JSONObject




data class Album (
    val id:Number?,
    @SerializedName("name") val name:String,
    val cover:String,
    val releaseDate:String,
    val description:String,
    val genre:String,
    val recordLabel:String,
    val tracks: Array<Track>
) {

    private val albumsRepository = AlbumRepository()

    fun parsedReleaseDate() : String {
        var parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(this.releaseDate)
        var formatter = SimpleDateFormat("dd/MMM/yyyy")

        return formatter.format(parser)
    }

    fun jsonPostString() : JsonObject {
        val paramObject = JsonObject()
        paramObject.addProperty("name", this.name)
        paramObject.addProperty("cover", this.cover)
        paramObject.addProperty("description", this.description)
        paramObject.addProperty("releaseDate", this.releaseDate)
        paramObject.addProperty("genre", this.genre)
        paramObject.addProperty("recordLabel", this.recordLabel)
        return paramObject
    }

    fun create(): Call<Album> {
        return albumsRepository.createAlbum(this)
    }
}