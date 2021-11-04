package com.example.vynilos.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.vynilos.viewmodels.AlbumsDetailActivityViewModel
import com.example.vynilos.R

class AlbumsDetailActivity: AppCompatActivity() {
    private lateinit var albumsDetailActivityViewModel: AlbumsDetailActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_album)
    }
}