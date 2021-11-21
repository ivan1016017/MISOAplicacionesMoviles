package com.example.vynilos.repositories

import androidx.lifecycle.MutableLiveData
import com.example.vynilos.models.Collector
import com.example.vynilos.network.NetworkServiceAdapter

class CollectorsRepository {
    private var serviceAdapter = NetworkServiceAdapter()

    fun getCollectors(liveDataList: MutableLiveData<List<Collector>>) {
        serviceAdapter.getCollectors(liveDataList)
    }

    fun getCollector(id: Number, liveDataList: MutableLiveData<Collector>) {
        serviceAdapter.getCollector(liveDataList, id)

    }
}