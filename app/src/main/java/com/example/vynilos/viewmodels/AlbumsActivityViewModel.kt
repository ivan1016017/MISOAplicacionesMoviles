package com.example.vynilos.viewmodels


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vynilos.models.Album
import com.example.vynilos.repositories.AlbumRepository

class AlbumsActivityViewModel : ViewModel() {
    private var liveDataList: MutableLiveData<List<Album>> = MutableLiveData()
    private val albumsRepository = AlbumRepository()

    fun getLiveDataObserver(): MutableLiveData<List<Album>> {
        return liveDataList
    }

    fun makeApiCall() {
        albumsRepository.getAlbums(liveDataList)
    }
}