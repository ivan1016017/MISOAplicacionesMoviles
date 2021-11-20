package com.example.vynilos.models

import android.content.pm.SigningInfo
import java.text.SimpleDateFormat

class Album (
    val id:Number,
    val name:String,
    val cover:String,
    val releaseDate:String,
    val description:String,
    val genre:String,
    val recordLabel:String
) {



    fun parsedReleaseDate() : String {
        var parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(this.releaseDate)
        var formatter = SimpleDateFormat("dd/MMM/yyyy")

        return formatter.format(parser)
    }
}