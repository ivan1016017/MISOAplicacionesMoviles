package com.example.vynilos.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vynilos.models.Album
import com.example.vynilos.repositories.AlbumRepository

class AlbumDetailViewModel: ViewModel() {
    private var liveData: MutableLiveData<Album> = MutableLiveData()
    private val albumsRepository = AlbumRepository()

    fun getLiveDataObserver(): MutableLiveData<Album> {
        return liveData
    }

    fun makeApiCall(id: Number) {
        albumsRepository.getAlbum(id, liveData)
    }

}