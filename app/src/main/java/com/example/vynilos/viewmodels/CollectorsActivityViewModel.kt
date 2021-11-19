package com.example.vynilos.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vynilos.models.Artist
import com.example.vynilos.models.Collector
import com.example.vynilos.repositories.CollectorsRepository

class CollectorsActivityViewModel: ViewModel() {
    private var liveDataList: MutableLiveData<List<Collector>> = MutableLiveData()
    private val collectorsRepository = CollectorsRepository()

    fun getLiveDataObserver(): MutableLiveData<List<Collector>> {
        return liveDataList
    }

    fun makeApiCall() {
        collectorsRepository.getCollectors(liveDataList)

    }
}