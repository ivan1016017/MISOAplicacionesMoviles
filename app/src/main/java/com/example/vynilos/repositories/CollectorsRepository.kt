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
        val call = service.getCollector("/collectors/$id")

        call.enqueue(object : Callback<Collector> {
            override fun onFailure(call: Call<Collector>, t: Throwable) {
                //#Need to figureout how to handle error
            }
            override fun onResponse(call: Call<Collector>, response: Response<Collector>) {
                liveDataList.postValue(response.body())
            }
        })
    }
}