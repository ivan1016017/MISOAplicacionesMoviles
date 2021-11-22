package com.example.vynilos.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vynilos.R
import com.example.vynilos.databinding.ActivityArtistsBinding
import com.example.vynilos.viewmodels.ArtistsActivityViewModel
import com.example.vynilos.views.adapters.ArtistAdapter


class ArtistsActivity:AppCompatActivity() {
    private lateinit var binding: ActivityArtistsBinding
    private lateinit var adapter: ArtistAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArtistsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbarText()
        handleBackClick()
        initViewModel()
        initRecyclerView()

    }

    private fun handleBackClick() {
        binding.toolbar.leftIcon.setOnClickListener { view ->
            this.finish()
        }
    }

    private fun setToolbarText() {
        binding.toolbar.toolbarText.text = getString(R.string.artists)
    }

    private fun initRecyclerView() {
        adapter = ArtistAdapter()
        binding.rvArtists.layoutManager = LinearLayoutManager(this)
        binding.rvArtists.adapter = adapter
    }

    private fun initViewModel() {
        val viewModel = ViewModelProvider(this).get(ArtistsActivityViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this, {
            adapter.setAlbums(it)
            adapter.notifyDataSetChanged()
        })
        viewModel.makeApiCall()
    }


}
