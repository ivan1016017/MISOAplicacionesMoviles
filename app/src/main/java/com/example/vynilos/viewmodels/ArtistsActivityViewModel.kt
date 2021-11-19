package com.example.vynilos.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vynilos.models.Artist
import com.example.vynilos.repositories.ArtistsRepository


class ArtistsActivityViewModel: ViewModel() {
    private var liveDataList: MutableLiveData<List<Artist>> = MutableLiveData()
    private val artistsRepository = ArtistsRepository()

    fun getLiveDataObserver(): MutableLiveData<List<Artist>> {
        return liveDataList
    }

    fun makeApiCall() {
        artistsRepository.getArtists(liveDataList)

    }
}