package com.example.vynilos.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vynilos.MainActivity
import com.example.vynilos.R
import com.example.vynilos.views.adapters.AlbumAdapter
import com.example.vynilos.databinding.ActivityCollectorsBinding
import com.example.vynilos.viewmodels.CollectorsActivityViewModel

class CollectorsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCollectorsBinding
    private lateinit var adapter: AlbumAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCollectorsBinding.inflate(layoutInflater)
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
            goToMainView(view)
        }
    }

    private fun initRecyclerView() {
        adapter = AlbumAdapter()
        binding.rvCollectors.layoutManager = LinearLayoutManager(this)
        binding.rvCollectors.adapter = adapter
    }

    private fun initViewModel() {
        val viewModel = ViewModelProvider(this).get(CollectorsActivityViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this, Observer {
            adapter.setCollectors(it)
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