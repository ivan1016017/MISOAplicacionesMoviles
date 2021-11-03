package com.example.vynilos.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vynilos.apis.ApiService
import com.example.vynilos.apis.RetrofitInstance
import com.example.vynilos.models.DetailAlbum
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumsDetailActivityViewModel: ViewModel() {
    lateinit var liveDataList: MutableLiveData<List<DetailAlbum>>

    init {
        liveDataList = MutableLiveData()
    }

    fun getLiveDataObserver(): MutableLiveData<List<DetailAlbum>> {
        return liveDataList
    }

    fun makeApiCall() {
        val retrofitInstance = RetrofitInstance.getRetrofitInstance()
        val retroService = retrofitInstance.create(ApiService::class.java)
        val call = retroService.getAlbumsDetail("/albums/103")

        call.enqueue(object : Callback<List<DetailAlbum>> {
            override fun onFailure(call: Call<List<DetailAlbum>>, t: Throwable) {
                liveDataList.postValue(emptyList())
            }
            override fun onResponse(call: Call<List<DetailAlbum>>, response: Response<List<DetailAlbum>>) {
                liveDataList.postValue(response.body())
            }
        })

    }
}