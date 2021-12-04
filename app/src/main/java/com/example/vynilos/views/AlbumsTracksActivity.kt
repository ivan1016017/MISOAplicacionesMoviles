package com.example.vynilos.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.vynilos.R
import com.example.vynilos.databinding.ActivityAlbumsTracksBinding
import com.example.vynilos.models.Track
import com.example.vynilos.network.NetworkServiceAdapter
import com.example.vynilos.viewmodels.AlbumDetailViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class AlbumsTracksActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAlbumsTracksBinding
    private var serviceAdapter = NetworkServiceAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumsTracksBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel = ViewModelProvider(this).get(AlbumDetailViewModel::class.java)
        var toNumberAlbumId : Number = 0
        val albumId = intent.getStringExtra("albumId")
        if (albumId != null) {
            toNumberAlbumId = (albumId.toInt())
        }

        setToolbarText()
        handleBackClick()
        val postButton: Button = findViewById(R.id.btn_create_tie_track_to_album)

        postButton.setOnClickListener {
            if (validateForm()) {
                val nameTxt: TextView = findViewById(R.id.txt_name)
                val durationTxt: TextView = findViewById(R.id.txt_duration)
                val name = nameTxt.text.toString()
                val duration = durationTxt.text.toString()
                createTrackToAlbum(name, duration, toNumberAlbumId)
//                this.finish()
            }
        }

    }

    private fun setToolbarText() {
        binding.toolbar.toolbarText.text = getString(R.string.tie_track_to_album)
    }

    private fun handleBackClick() {
        binding.toolbar.leftIcon.setOnClickListener { view ->
            this.finish()
        }
    }

    private fun validateForm(): Boolean {
        var valid = true
        var error_text = ""
        binding.tvErrors.text = ""

        if(isInvalidName()) {
            valid = false
            error_text = error_text + "Nombre del track requerido \n"
        }

        if(isInvalidDuration()) {
            valid = false
            error_text = error_text + "Duracion del track requerido \n"
        }

        if(!valid) {
            binding.tvErrors.text = error_text
        }

        return valid
    }

    private fun isInvalidName():Boolean {
        val nameTxt : TextView = findViewById(R.id.txt_name)
        val name = nameTxt.text.toString()
        return name.isNullOrEmpty()
    }

    private fun isInvalidDuration():Boolean {

        val durationTxt : TextView = findViewById(R.id.txt_duration)
        val duration = durationTxt.text.toString()
        return duration.isNullOrEmpty()
    }



    fun createTrackToAlbum(name: String, duration: String, id: Number){
        val view = this
        val albumId = intent.getStringExtra("albumId")
        val call = serviceAdapter.createTrackToAlbum(name, duration, id)
        call.enqueue(object : Callback<Track> {
            override fun onFailure(call: Call<Track>, t: Throwable) {
                //#Need to figureout how to handle error
            }

            override fun onResponse(call: Call<Track>, response: Response<Track>) {
//                    Handler(Looper.getMainLooper()).post {
//                        liveDataList.postValue(response.body())
//                    }

                view.finish()
                val intent = Intent(view, AlbumsDetailActivity::class.java)
                intent.putExtra("albumId", albumId)
                view.startActivity(intent)

            }
        })
    }



}