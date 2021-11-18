package com.example.vynilos.repositories

import androidx.lifecycle.MutableLiveData
import com.example.vynilos.models.Collector
import com.example.vynilos.network.ApiService
import com.example.vynilos.network.NetworkServiceAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CollectorsRepository(){
    private var service = NetworkServiceAdapter.getRetrofitInstance().create(ApiService::class.java)

    fun getCollectors(liveDataList: MutableLiveData<List<Collector>>) {
        val call = service.getCollectors("/collectors")

        call.enqueue(object : Callback<List<Collector>> {
            override fun onFailure(call: Call<List<Collector>>, t: Throwable) {
                liveDataList.postValue(emptyList())
            }
            override fun onResponse(call: Call<List<Collector>>, response: Response<List<Collector>>) {
                liveDataList.postValue(response.body())
            }
        })
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