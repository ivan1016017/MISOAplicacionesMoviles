package com.example.vynilos.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.vynilos.R
import com.example.vynilos.databinding.ActivityDetailAlbumBinding
import com.example.vynilos.viewmodels.AlbumDetailViewModel
import com.squareup.picasso.Picasso

class AlbumsDetailActivity: AppCompatActivity() {
    private lateinit var binding: ActivityDetailAlbumBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val albumId = intent.getStringExtra("albumId")
        if (albumId != null) {
            initViewModel(albumId.toInt())
        }
        handleBackClick()
        bindAlbumDetailEvents()
    }

    private fun bindAlbumDetailEvents() {
        val trackTiedToAlbumButton: Button = findViewById(R.id.btn_tie_track_to_album)
        trackTiedToAlbumButton.setOnClickListener { view ->
            openTrackTiedToAlbumView(view)
        }
    }

    private fun openTrackTiedToAlbumView(view: View) {
        val intent = Intent(this, AlbumsTracksActivity::class.java).apply {
        }
        startActivity(intent)
    }

    private fun handleBackClick() {
        binding.toolbar.leftIcon.setOnClickListener { view ->
            this.finish()
        }
    }

    private fun initViewModel(albumId: Number ) {
        val viewModel = ViewModelProvider(this).get(AlbumDetailViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this, {
            binding.title.text = it.name
            binding.tvDescription.text = it.description
            binding.gender.text = it.genre
            binding.date.text = it.parsedReleaseDate()
            Picasso.get().load(it.cover).into(binding.ivCover)
        })

        viewModel.makeApiCall(albumId)
    }

}