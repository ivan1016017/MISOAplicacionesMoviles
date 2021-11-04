package com.example.vynilos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.vynilos.views.AlbumsActivity
import com.example.vynilos.views.ArtistsActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindMenuEvents()
    }

    private fun bindMenuEvents() {
        val albumsMenuButton: Button = findViewById(R.id.btn_albums_menu)
        albumsMenuButton.setOnClickListener { view ->
            openAlbumListView(view)
        }
        val artistMenuButton: Button = findViewById(R.id.btn_artists_menu)
        artistMenuButton.setOnClickListener { view ->
            openArtistListView(view)
        }
    }

    private fun openAlbumListView(view: View) {
        val intent = Intent(this, AlbumsActivity::class.java).apply {
        }
        startActivity(intent)
    }

    private fun openArtistListView(view: View) {
        val intent = Intent(this, ArtistsActivity::class.java).apply {
        }
        startActivity(intent)
    }
}