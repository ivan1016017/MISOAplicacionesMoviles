package com.example.vynilos.network
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import com.example.vynilos.models.Album
import com.example.vynilos.models.Artist
import com.example.vynilos.models.Collector
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkServiceAdapter {
   companion object {
        val BASE_URL = "https://grupo-11-android.herokuapp.com/"

        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder().
                baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build()
        }
    }

    fun getAlbums(liveDataList: MutableLiveData<List<Album>>) {
        CoroutineScope(Dispatchers.IO).launch {
            var service = getRetrofitInstance().create(ApiService::class.java)
            val call: Response<List<Album>> = service.getAlbums("/albums")
            println("On after call")
            val response = call.body()
            println("On after response")
            //Return to main thread that draws the UI
            Handler(Looper.getMainLooper()).post {
                if (call.isSuccessful) {
                    liveDataList.postValue(response)
                } else {
                    //println("##########################################################3")
                    //liveDataList.postValue(emptyList())
                }
            }

            //call.enqueue(object : Callback<List<Album>> {
            //    override fun onFailure(call: Call<List<Album>>, t: Throwable) {
            //        liveDataList.postValue(emptyList())
            //    }

            //    override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
            //        liveDataList.postValue(response.body())
            //    }
            //})
        }
    }

    fun getAlbum(liveDataList: MutableLiveData<Album>, id: Number) {
        var service = getRetrofitInstance().create(ApiService::class.java)
        val call = service.getAlbum("/albums/" + id)

        call.enqueue(object : Callback<Album> {
            override fun onFailure(call: Call<Album>, t: Throwable) {
                //#Need to figureout how to handle error
            }
            override fun onResponse(call: Call<Album>, response: Response<Album>) {
                liveDataList.postValue(response.body())
            }
        })
    }

    fun getArtists(liveDataList: MutableLiveData<List<Artist>>) {
        var service = getRetrofitInstance().create(ApiService::class.java)
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

    fun getCollectors(liveDataList: MutableLiveData<List<Collector>>) {
        var service = getRetrofitInstance().create(ApiService::class.java)
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
}