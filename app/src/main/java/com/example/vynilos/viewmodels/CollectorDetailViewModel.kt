package com.example.vynilos.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vynilos.models.Collector
import com.example.vynilos.repositories.CollectorsRepository

class CollectorDetailViewModel(): ViewModel() {
    lateinit var liveData: MutableLiveData<Collector>
    private val collectorRepository = CollectorsRepository()

    init {
        liveData = MutableLiveData()
    }

    fun getLiveDataObserver(): MutableLiveData<Collector> {
        return liveData
    }

    fun makeApiCall(id: Number) {
        collectorRepository.getCollector(id, liveData)
    }
}