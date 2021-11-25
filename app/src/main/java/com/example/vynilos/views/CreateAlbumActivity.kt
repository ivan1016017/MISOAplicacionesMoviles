package com.example.vynilos.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vynilos.databinding.ActivityCollectorsBinding
import com.example.vynilos.databinding.ActivityCreateAlbumBinding

class CreateAlbumActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateAlbumBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleBackClick()
    }

    private fun handleBackClick() {
        binding.toolbar.leftIcon.setOnClickListener { view ->
            this.finish()
        }
    }

}