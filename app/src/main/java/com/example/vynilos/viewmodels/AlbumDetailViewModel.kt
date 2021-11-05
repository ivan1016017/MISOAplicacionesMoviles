package com.example.vynilos.viewmodels

import androidx.lifecycle.MutableLiveData
import com.example.vynilos.models.DetailAlbum
import com.example.vynilos.network.ApiService
import com.example.vynilos.network.NetworkServiceAdapter

class AlbumDetailViewModel {
    private var service = NetworkServiceAdapter.getRetrofitInstance().create(ApiService::class.java)

    val album = MutableLiveData<List<DetailAlbum>>()
    val albumLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        val album1 = DetailAlbum(100, "Buscando America", "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg", "Buscando América es el primer álbum de la banda de Rubén Blades y Seis del Solar lanzado en 1984. La producción, bajo el sello Elektra, fusiona diferentes ritmos musicales tales como la salsa, reggae, rock, y el jazz latino. El disco fue grabado en Eurosound Studios en Nueva York entre mayo y agosto de 1983.", "1984-08-01T00:00:00.000Z")
        val albumList = arrayListOf<DetailAlbum>(album1)

        album.value = albumList
        albumLoadError.value = false
        loading.value = false
    }
}