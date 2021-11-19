package com.example.vynilos.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vynilos.R
import com.example.vynilos.databinding.ActivityAlbumsBinding
import com.example.vynilos.viewmodels.AlbumsActivityViewModel
import com.example.vynilos.views.adapters.AlbumAdapter

class AlbumsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAlbumsBinding
    private lateinit var adapter: AlbumAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbarText()
        handleBackClick()
        initViewModel()
        initRecyclerView()
   }

    private fun setToolbarText() {
        binding.toolbar.toolbarText.text = getString(R.string.albums)
    }

    private fun handleBackClick() {
        binding.toolbar.leftIcon.setOnClickListener { view ->
            this.finish()
        }
    }

    private fun initRecyclerView() {
        adapter = AlbumAdapter()
        binding.rvAlbums.layoutManager = LinearLayoutManager(this)
        binding.rvAlbums.adapter = adapter
    }

    private fun initViewModel() {
        val viewModel = ViewModelProvider(this).get(AlbumsActivityViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this, {
            adapter.setAlbums(it)
            adapter.notifyDataSetChanged()
        })
        viewModel.makeApiCall()
    }

}