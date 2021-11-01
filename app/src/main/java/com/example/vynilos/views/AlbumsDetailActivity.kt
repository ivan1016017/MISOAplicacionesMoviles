package com.example.vynilos.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.vynilos.adapters.AlbumAdapter
import com.example.vynilos.databinding.ActivityDetailAlbumBinding
import com.example.vynilos.models.Album

class AlbumsDetailActivity: AppCompatActivity() {
    private lateinit var binding: ActivityDetailAlbumBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}