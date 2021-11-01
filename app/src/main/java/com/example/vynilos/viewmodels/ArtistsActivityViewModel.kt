package com.example.vynilos.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vynilos.apis.ApiService
import com.example.vynilos.apis.RetrofitInstance
import com.example.vynilos.models.Artist
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArtistsActivityViewModel: ViewModel() {

    lateinit var liveDataList: MutableLiveData<List<Artist>>

    init {
        liveDataList = MutableLiveData()
    }

    fun getLiveDataObserver(): MutableLiveData<List<Artist>> {
        return liveDataList
    }

    fun makeApiCall() {
        val retrofitInstance = RetrofitInstance.getRetrofitInstance()
        val retroService = retrofitInstance.create(ApiService::class.java)
        val call = retroService.getArtists("/bands")

        call.enqueue(object : Callback<List<Artist>> {
            override fun onFailure(call: Call<List<Artist>>, t: Throwable) {
                liveDataList.postValue(emptyList())
            }
            override fun onResponse(call: Call<List<Artist>>, response: Response<List<Artist>>) {
                liveDataList.postValue(response.body())
            }
        })

    }
}