package com.example.vynilos.repositories

import androidx.lifecycle.MutableLiveData
import com.example.vynilos.models.Artist
import com.example.vynilos.network.ApiService
import com.example.vynilos.network.NetworkServiceAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArtistsRepository {
    private var service = NetworkServiceAdapter.getRetrofitInstance().create(ApiService::class.java)

    fun getArtists(liveDataList: MutableLiveData<List<Artist>>) {
        val call = service.getArtists("/bands")

        call.enqueue(object : Callback<List<Artist>> {
            override fun onFailure(call: Call<List<Artist>>, t: Throwable) {
                liveDataList.postValue(emptyList())
            }
            override fun onResponse(call: Call<List<Artist>>, response: Response<List<Artist>>) {
                liveDataList.postValue(response.body())
            }
        })
    }
    fun getArtist(id: Number, liveDataList: MutableLiveData<Artist>) {
        val call = service.getArtist("/bands/$id")

        call.enqueue(object : Callback<Artist> {
            override fun onFailure(call: Call<Artist>, t: Throwable) {
                //#Need to figureout how to handle error
            }
            override fun onResponse(call: Call<Artist>, response: Response<Artist>) {
                liveDataList.postValue(response.body())
            }
        })
    }
}