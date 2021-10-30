package com.example.vynilos.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vynilos.adapters.AlbumAdapter
import com.example.vynilos.apis.ApiService
import com.example.vynilos.databinding.ActivityAlbumsBinding
import com.example.vynilos.models.Album
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AlbumsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAlbumsBinding
    private lateinit var adapter: AlbumAdapter
    private val albums = mutableListOf<Album>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        getAlbums()
    }

    private fun initRecyclerView() {
        adapter = AlbumAdapter(albums)
        binding.rvAlbums.layoutManager = LinearLayoutManager(this)
        binding.rvAlbums.adapter = adapter
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://grupo-11-android.herokuapp.com/").
                addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun getAlbums() {
        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<List<Album>> = getRetrofit().create(ApiService::class.java).getAlbums("/albums")
            val albumsResponse: List<Album>? = call.body()
            runOnUiThread {
                if(call.isSuccessful) {
                    val showAlbums = albumsResponse ?: emptyList()
                    albums.clear()
                    albums.addAll(showAlbums)
                    adapter.notifyDataSetChanged()
                } else {
                    showError()
                }
            }
        }
    }

    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }
}