package com.example.vynilos.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vynilos.R
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vynilos.adapters.ArtistAdapter
import com.example.vynilos.apis.ApiService
import com.example.vynilos.databinding.ActivityArtistsBinding
import com.example.vynilos.models.Album
import com.example.vynilos.models.Artist
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ArtistsActivity:AppCompatActivity() {
    private lateinit var binding: ActivityArtistsBinding
    private lateinit var adapter: ArtistAdapter
    private val artists = mutableListOf<Artist>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArtistsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        getAlbums()
    }

    private fun initRecyclerView() {
        adapter = ArtistAdapter(artists)
        binding.rvArtists.layoutManager = LinearLayoutManager(this)
        binding.rvArtists.adapter = adapter
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://grupo-11-android.herokuapp.com/").
        addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun getAlbums() {
        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<List<Artist>> = getRetrofit().create(ApiService::class.java).getArtists("/musicians")
            val artistsResponse: List<Artist>? = call.body()
            runOnUiThread {
                if(call.isSuccessful) {
                    val showArtists = artistsResponse ?: emptyList()
                    artists.clear()
                    artists.addAll(showArtists)
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
