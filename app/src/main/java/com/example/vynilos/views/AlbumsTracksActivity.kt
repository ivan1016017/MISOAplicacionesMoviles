package com.example.vynilos.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vynilos.R
import com.example.vynilos.databinding.ActivityAlbumsTracksBinding

class AlbumsTracksActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAlbumsTracksBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumsTracksBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbarText()
    }

    private fun setToolbarText() {
        binding.toolbar.toolbarText.text = getString(R.string.tie_track_to_album)
    }

}