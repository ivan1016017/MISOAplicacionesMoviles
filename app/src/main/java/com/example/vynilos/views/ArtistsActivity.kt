package com.example.vynilos.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.vynilos.R
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vynilos.adapters.ArtistAdapter
import com.example.vynilos.databinding.ActivityArtistsBinding
import com.example.vynilos.viewmodels.ArtistsActivityViewModel
import androidx.lifecycle.Observer
import com.example.vynilos.MainActivity


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
            goToMainView(view)
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
        viewModel.getLiveDataObserver().observe(this, Observer {
            adapter.setAlbums(it)
            adapter.notifyDataSetChanged()
        })
        viewModel.makeApiCall()
    }

    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

    private fun goToMainView(view: View) {
        val intent = Intent(this, MainActivity::class.java).apply {
        }
        startActivity(intent)
    }

}
