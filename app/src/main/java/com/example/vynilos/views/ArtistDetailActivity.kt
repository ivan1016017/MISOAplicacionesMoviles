package com.example.vynilos.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.vynilos.databinding.ActivityArtistsDetailBinding
import com.example.vynilos.databinding.ActivityDetailAlbumBinding
import com.example.vynilos.viewmodels.AlbumDetailViewModel
import com.example.vynilos.viewmodels.ArtistDetailViewModel
import com.squareup.picasso.Picasso

class ArtistDetailActivity: AppCompatActivity() {
    private lateinit var binding: ActivityArtistsDetailBinding
    private val viewModel: ArtistDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArtistsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var artistId = intent.getStringExtra("artistId")
        if (artistId != null) {
            initViewModel(artistId.toInt())
        }
        handleBackClick()
    }

    private fun handleBackClick() {
        binding.toolbar.leftIcon.setOnClickListener { view ->
            this.finish()
        }
    }

    private fun initViewModel(albumId: Number ) {
        val viewModel = ViewModelProvider(this).get(ArtistDetailViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this, Observer {
            binding.title.text = it.name
            binding.tvDescription.text = it.description
            binding.gender.text = it.genre
            binding.date.text = it.releaseDate
            Picasso.get().load(it.cover).into(binding.ivCover)
        })

        viewModel.makeApiCall(albumId)
    }

}