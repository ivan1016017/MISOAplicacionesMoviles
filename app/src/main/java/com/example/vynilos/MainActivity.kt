package com.example.vynilos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.vynilos.views.AlbumsActivity
import com.example.vynilos.views.ArtistsActivity
import com.example.vynilos.views.CollectorsActivity
import com.example.vynilos.views.CreateAlbumActivity


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

        val collectorsMenuButton: Button = findViewById(R.id.btn_collectors_menu)
        collectorsMenuButton.setOnClickListener { view ->
            openCollectorListView(view)
        }

        val createAlbumMenuButton: Button = findViewById(R.id.btn_create_album)
        createAlbumMenuButton.setOnClickListener { view ->
            openCreateAlbumView(view)
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

    private fun openCollectorListView(view: View) {
        val intent = Intent(this, CollectorsActivity::class.java).apply {
        }
        startActivity(intent)
    }

    private fun openCreateAlbumView(view: View) {
        val intent = Intent(this, CreateAlbumActivity::class.java).apply {
        }
        startActivity(intent)
    }
}