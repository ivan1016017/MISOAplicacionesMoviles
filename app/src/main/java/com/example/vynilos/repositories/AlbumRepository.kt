package com.example.vynilos.repositories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.vynilos.models.Album
import com.example.vynilos.network.ApiService
import com.example.vynilos.network.NetworkServiceAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumRepository {
    private var serviceAdapter = NetworkServiceAdapter()

    fun getAlbums(liveDataList: MutableLiveData<List<Album>>) {
        serviceAdapter.getAlbums(liveDataList)
    }

    fun getAlbum(id: Number, liveDataList: MutableLiveData<Album>) {
        serviceAdapter.getAlbum(liveDataList, id)
    }
}