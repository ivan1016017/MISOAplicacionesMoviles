package com.example.vynilos.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vynilos.R
import com.example.vynilos.databinding.ItemAlbumBinding
import com.example.vynilos.models.DetailAlbum
import com.squareup.picasso.Picasso

class AlbumDetailAdapter(): RecyclerView.Adapter<AlbumDetailAdapter.AlbumDetailHolder>() {
    private var album: List<DetailAlbum>? = null

    class AlbumViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun getItemCount(): Int {
        if(album == null) return 0
        else return album?.size!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumDetailHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_album, parent, false)
        return AlbumDetailHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumDetailHolder, position: Int) {
        val item = album.get(position)
        holder.bind(item!!)
    }
    class AlbumDetailHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = 
    }
}