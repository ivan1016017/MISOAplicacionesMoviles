package com.example.vynilos.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.vynilos.R
import com.example.vynilos.repositories.AlbumDetailRepository

class AlbumsDetailActivity: AppCompatActivity() {

    private lateinit var viewModel: ListViewModel
    private val albumRepository = AlbumDetailRepository(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_album)
    }
}