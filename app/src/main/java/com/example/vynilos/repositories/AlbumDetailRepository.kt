package com.example.vynilos.repositories

import androidx.lifecycle.MutableLiveData
import com.example.vynilos.models.Album
import com.example.vynilos.models.DetailAlbum
import com.example.vynilos.network.ApiService
import com.example.vynilos.network.NetworkServiceAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumDetailRepository():
    private var service = NetworkServiceAdapter.getRetrofitInstance().create(ApiService::class.java)

    fun getAlbumDetail(liveDataList: MutableLiveData<List<DetailAlbum>>) {
        val call = service.getAlbums("/albums/100")

    call.enqueue(object : Callback<List<DetailAlbum>> {
        override fun onFailure(call: Call<List<DetailAlbum>>, t: Throwable) {
            liveDataList.postValue(emptyList())
        }
        override fun onResponse(call: Call<List<DetailAlbum>>, response: Response<List<Album>>) {
            liveDataList.postValue(response.body())
        }
    })
}
}
