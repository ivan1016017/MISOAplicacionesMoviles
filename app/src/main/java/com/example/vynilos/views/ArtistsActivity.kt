package com.example.vynilos.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vynilos.R
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vynilos.adapters.ArtistAdapter
import com.example.vynilos.databinding.ActivityArtistsBinding
import com.example.vynilos.viewmodels.ArtistsActivityViewModel
import androidx.lifecycle.Observer



class ArtistsActivity:AppCompatActivity() {
    private lateinit var binding: ActivityArtistsBinding
    private lateinit var adapter: ArtistAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArtistsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        initRecyclerView()

    }

    private fun initRecyclerView() {
        adapter = ArtistAdapter()
        binding.rvArtists.layoutManager = LinearLayoutManager(this)
        binding.rvArtists.adapter = adapter
    }



    private fun initViewModel() {
        val viewModel = ViewModelProvider(this).get(ArtistsActivityViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this, Observer {
            adapter.setAlbums(it)
            adapter.notifyDataSetChanged()
        })
        viewModel.makeApiCall()
    }

    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }


}
