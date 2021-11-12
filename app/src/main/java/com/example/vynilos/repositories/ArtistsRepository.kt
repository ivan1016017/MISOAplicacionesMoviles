package com.example.vynilos.repositories

import androidx.lifecycle.MutableLiveData
import com.example.vynilos.models.Artist
import com.example.vynilos.network.ApiService
import com.example.vynilos.network.NetworkServiceAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArtistsRepository {
    private var serviceAdapter = NetworkServiceAdapter()

    fun getArtists(liveDataList: MutableLiveData<List<Artist>>) {
        serviceAdapter.getArtists(liveDataList)
    }
}