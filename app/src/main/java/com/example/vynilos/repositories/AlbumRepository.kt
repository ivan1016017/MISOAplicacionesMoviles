package com.example.vynilos.repositories

import androidx.lifecycle.MutableLiveData
import com.example.vynilos.models.Album
import com.example.vynilos.network.NetworkServiceAdapter
import retrofit2.Call

class AlbumRepository {
    private var serviceAdapter = NetworkServiceAdapter()

    fun getAlbums(liveDataList: MutableLiveData<List<Album>>) {
        serviceAdapter.getAlbums(liveDataList)
    }

    fun getAlbum(id: Number, liveDataList: MutableLiveData<Album>) {
        serviceAdapter.getAlbum(liveDataList, id)
    }

    fun createAlbum(album: Album): Call<Album> {
        return serviceAdapter.createAlbum(album)
    }
}