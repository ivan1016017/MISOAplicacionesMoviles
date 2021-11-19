package com.example.vynilos.repositories

import androidx.lifecycle.MutableLiveData
import com.example.vynilos.models.Artist
import com.example.vynilos.network.NetworkServiceAdapter

class ArtistsRepository {
    private var serviceAdapter = NetworkServiceAdapter()

    fun getArtists(liveDataList: MutableLiveData<List<Artist>>) {
        serviceAdapter.getArtists(liveDataList)
    }
    fun getArtist(id: Number, liveDataList: MutableLiveData<Artist>) {
        serviceAdapter.getArtist(liveDataList, id)

    }
}