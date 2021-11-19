package com.example.vynilos.repositories

import androidx.lifecycle.MutableLiveData
import com.example.vynilos.models.Collector
import com.example.vynilos.network.ApiService
import com.example.vynilos.network.NetworkServiceAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CollectorsRepository(){
    private var serviceAdapter = NetworkServiceAdapter()

    fun getCollectors(liveDataList: MutableLiveData<List<Collector>>) {
        serviceAdapter.getCollectors(liveDataList)
    }

    fun getCollector(id: Number, liveDataList: MutableLiveData<Collector>) {
        serviceAdapter.getCollector(liveDataList, id)

    }
}