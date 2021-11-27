package com.example.vynilos.network
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import com.example.vynilos.models.Album
import com.example.vynilos.models.Artist
import com.example.vynilos.models.Collector
import com.example.vynilos.models.Track
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
        private const val BASE_URL = "https://grupo-11-android.herokuapp.com/"

        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder().
                baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build()
        }
    }

    fun getAlbums(liveDataList: MutableLiveData<List<Album>>) {
        CoroutineScope(Dispatchers.IO).launch {
            val service = getRetrofitInstance().create(ApiService::class.java)
            val call = service.getAlbums("/albums")

            call.enqueue(object : Callback<List<Album>> {
                override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                    Handler(Looper.getMainLooper()).post {
                        liveDataList.postValue(emptyList())
                    }
                }
                override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                    //Return to main thread that draws the UI

                    Handler(Looper.getMainLooper()).post {
                        liveDataList.postValue(response.body())
                    }

                }
            })
        }
    }

    fun getAlbum(liveDataList: MutableLiveData<Album>, id: Number) {
        CoroutineScope(Dispatchers.IO).launch {
            val service = getRetrofitInstance().create(ApiService::class.java)
            val call = service.getAlbum("/albums/$id")

            call.enqueue(object : Callback<Album> {
                override fun onFailure(call: Call<Album>, t: Throwable) {
                    //#Need to figureout how to handle error
                }

                override fun onResponse(call: Call<Album>, response: Response<Album>) {
                    Handler(Looper.getMainLooper()).post {
                        liveDataList.postValue(response.body())
                    }
                }
            })
        }
    }

    fun getArtists(liveDataList: MutableLiveData<List<Artist>>) {
        CoroutineScope(Dispatchers.IO).launch {
            val service = getRetrofitInstance().create(ApiService::class.java)
            val call = service.getArtists("/bands")

            call.enqueue(object : Callback<List<Artist>> {
                override fun onFailure(call: Call<List<Artist>>, t: Throwable) {
                    liveDataList.postValue(emptyList())
                }

                override fun onResponse(call: Call<List<Artist>>, response: Response<List<Artist>>) {
                    Handler(Looper.getMainLooper()).post {
                        liveDataList.postValue(response.body())
                    }
                }
            })
        }
    }

    fun getArtist(liveDataList: MutableLiveData<Artist>, id: Number) {
        CoroutineScope(Dispatchers.IO).launch {
            val service = getRetrofitInstance().create(ApiService::class.java)
            val call = service.getArtist("/bands/$id")

            call.enqueue(object : Callback<Artist> {
                override fun onFailure(call: Call<Artist>, t: Throwable) {
                    //#Need to figureout how to handle error
                }

                override fun onResponse(call: Call<Artist>, response: Response<Artist>) {
                    Handler(Looper.getMainLooper()).post {
                        liveDataList.postValue(response.body())
                    }
                }
            })
        }
    }

    fun getCollectors(liveDataList: MutableLiveData<List<Collector>>) {
        CoroutineScope(Dispatchers.IO).launch {
            val service = getRetrofitInstance().create(ApiService::class.java)
            val call = service.getCollectors("/collectors")

            call.enqueue(object : Callback<List<Collector>> {
                override fun onFailure(call: Call<List<Collector>>, t: Throwable) {
                    Handler(Looper.getMainLooper()).post {
                        liveDataList.postValue(emptyList())
                    }
                }

                override fun onResponse(call: Call<List<Collector>>, response: Response<List<Collector>>) {
                    Handler(Looper.getMainLooper()).post {
                        liveDataList.postValue(response.body())
                    }
                }
            })
        }
    }

    fun getCollector(liveDataList: MutableLiveData<Collector>, id: Number) {
        CoroutineScope(Dispatchers.IO).launch {
            val service = getRetrofitInstance().create(ApiService::class.java)

            val call = service.getCollector("/collectors/$id")

            call.enqueue(object : Callback<Collector> {
                override fun onFailure(call: Call<Collector>, t: Throwable) {
                    //#Need to figureout how to handle error
                }

                override fun onResponse(call: Call<Collector>, response: Response<Collector>) {
                    Handler(Looper.getMainLooper()).post {
                        liveDataList.postValue(response.body())
                    }
                }
            })
        }
    }

    fun createTrackToAlbum(name: String, duration: String, id: Number) {
        CoroutineScope(Dispatchers.IO).launch {
            val service = getRetrofitInstance().create(ApiService::class.java)
            val track = Track(name = name, duration = duration)

            val call = service.createTrackToAlbum("/albums/$id/tracks",track)

            call.enqueue(object : Callback<Track> {
                override fun onFailure(call: Call<Track>, t: Throwable) {
                    //#Need to figureout how to handle error
                }

                override fun onResponse(call: Call<Track>, response: Response<Track>) {
//                    Handler(Looper.getMainLooper()).post {
//                        liveDataList.postValue(response.body())
//                    }
                    val addedTrack = response.body()
                    println(addedTrack)
                }
            })
        }
    }



}