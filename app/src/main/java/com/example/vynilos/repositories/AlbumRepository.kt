package com.example.vynilos.repositories

import androidx.lifecycle.MutableLiveData
import com.example.vynilos.models.Album
import com.example.vynilos.network.ApiService
import com.example.vynilos.network.NetworkServiceAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumRepository(){
    private var service = NetworkServiceAdapter.getRetrofitInstance().create(ApiService::class.java)

    fun getAlbums(liveDataList: MutableLiveData<List<Album>>) {
        val call = service.getAlbums("/albums")

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