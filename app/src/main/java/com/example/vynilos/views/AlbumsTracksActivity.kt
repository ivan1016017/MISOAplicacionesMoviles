package com.example.vynilos.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.vynilos.R
import com.example.vynilos.databinding.ActivityAlbumsTracksBinding
import com.example.vynilos.network.NetworkServiceAdapter
import com.google.android.material.textfield.TextInputEditText
import org.json.JSONObject

class AlbumsTracksActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAlbumsTracksBinding
    private var serviceAdapter = NetworkServiceAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumsTracksBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var toNumberAlbumId : Number = 0
        val albumId = intent.getStringExtra("albumId")
        if (albumId != null) {
            toNumberAlbumId = (albumId.toInt())
        }

        setToolbarText()
        handleBackClick()
        val postButton: Button = findViewById(R.id.btn_create_tie_track_to_album)

        postButton.setOnClickListener {
            val nameTxt : TextInputEditText = findViewById(R.id.txt_name)
            val durationTxt : TextInputEditText = findViewById(R.id.txt_duration)

            val name = nameTxt.text.toString()
            val duration = durationTxt.text.toString()

            val postParams = mapOf<String, Any>(
                "name" to nameTxt.text.toString(),
                "duration" to durationTxt.text.toString(),
            )
//            volleyBroker.instance.add(VolleyBroker.postRequest("collectors", JSONObject(postParams),
//                Response.Listener<JSONObject> { response ->
//                    // Display the first 500 characters of the response string.
//                    postResultTextView.text = "Response is: ${response.toString()}"
//                },
//                Response.ErrorListener {
//                    Log.d("TAG", it.toString())
//                    postResultTextView.text = "That didn't work!"
//                }
//            ))
            println(name)
            println(duration)
            println(toNumberAlbumId)
            createTrackToAlbum(name, duration, toNumberAlbumId)
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



    fun createTrackToAlbum(name: String, duration: String, id: Number){
        serviceAdapter.createTrackToAlbum(name, duration, id)
    }



}