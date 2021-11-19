package com.example.vynilos.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vynilos.models.Artist
import com.example.vynilos.repositories.ArtistsRepository

class ArtistDetailViewModel : ViewModel() {
    private var liveData: MutableLiveData<Artist> = MutableLiveData()
    private val artistRepository = ArtistsRepository()

    fun getLiveDataObserver(): MutableLiveData<Artist> {
        return liveData
    }

    fun makeApiCall(id: Number) {
        artistRepository.getArtist(id, liveData)
    }
}