package com.example.vynilos.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vynilos.models.Artist
import com.example.vynilos.models.Collector
import com.example.vynilos.repositories.CollectorsRepository

class CollectorsActivityViewModel: ViewModel() {
    lateinit var liveDataList: MutableLiveData<List<Collector>>
    private val collectorsRepository = CollectorsRepository()

    init {
        liveDataList = MutableLiveData()
    }

    fun getLiveDataObserver(): MutableLiveData<List<Collector>> {
        return liveDataList
    }

    fun makeApiCall() {
        collectorsRepository.getCollectors(liveDataList)

    }
}