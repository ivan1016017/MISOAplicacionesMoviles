package com.example.vynilos.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vynilos.R
import com.example.vynilos.databinding.ActivityDetailAlbumBinding
import com.example.vynilos.databinding.ItemTrackBinding
import com.example.vynilos.models.Track
import com.example.vynilos.viewmodels.AlbumDetailViewModel
import com.example.vynilos.views.adapters.AlbumAdapter
import com.example.vynilos.views.adapters.TrackAdapter
import com.squareup.picasso.Picasso

class AlbumsDetailActivity: AppCompatActivity() {
    private lateinit var binding: ActivityDetailAlbumBinding
    private lateinit var adapter: TrackAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val albumId = intent.getStringExtra("albumId")
        if (albumId != null) {
            initViewModel(albumId.toInt())
            bindAlbumDetailEvents(albumId)
        }
        handleBackClick()
        initRecyclerView()
    }

    private fun bindAlbumDetailEvents(albumId: String) {
        val trackTiedToAlbumButton: Button = findViewById(R.id.btn_tie_track_to_album)
        trackTiedToAlbumButton.setOnClickListener { view ->
            openTrackTiedToAlbumView(view, albumId)
        }
    }

    private fun openTrackTiedToAlbumView(view: View, albumId:String) {
        val intent = Intent(this, AlbumsTracksActivity::class.java).apply {
        }
        intent.putExtra("albumId", albumId )
        startActivity(intent)
    }

    private fun handleBackClick() {
        binding.toolbar.leftIcon.setOnClickListener { view ->
            this.finish()
        }
    }

    private fun initRecyclerView() {
        adapter = TrackAdapter()
        binding.rvTracks.layoutManager = LinearLayoutManager(this)
        binding.rvTracks.adapter = adapter
    }


    private fun initViewModel(albumId: Number ) {
        val viewModel = ViewModelProvider(this).get(AlbumDetailViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this, {
            adapter.setTracks(it.tracks.toList())
            adapter.notifyDataSetChanged()
            for (item in it.tracks) println(item.name)
            binding.title.text = it.name
            binding.tvDescription.text = it.description
            binding.gender.text = it.genre
            binding.date.text = it.parsedReleaseDate()
            Picasso.get().load(it.cover).into(binding.ivCover)
        })

        viewModel.makeApiCall(albumId)
    }

}