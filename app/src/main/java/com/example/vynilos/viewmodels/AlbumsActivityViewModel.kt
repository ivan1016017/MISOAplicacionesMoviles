package com.example.vynilos.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vynilos.apis.ApiService
import com.example.vynilos.apis.RetrofitInstance
import com.example.vynilos.models.Album
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumsActivityViewModel: ViewModel() {
    lateinit var liveDataList: MutableLiveData<List<Album>>

    init {
        liveDataList = MutableLiveData()
    }

    fun getLiveDataObserver(): MutableLiveData<List<Album>> {
        return liveDataList
    }

    fun makeApiCall() {
        val retrofitInstance = RetrofitInstance.getRetrofitInstance()
        val retroService = retrofitInstance.create(ApiService::class.java)
        val call = retroService.getAlbums("/albums")

        call.enqueue(object : Callback<List<Album>> {
            override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                liveDataList.postValue(emptyList())
            }
            override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                liveDataList.postValue(response.body())
            }
        })

    }
}