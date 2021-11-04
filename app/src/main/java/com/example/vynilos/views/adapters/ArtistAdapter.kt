package com.example.vynilos.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.vynilos.R
import com.example.vynilos.databinding.ItemArtistBinding
import com.example.vynilos.models.Album
import com.example.vynilos.models.Artist
import com.squareup.picasso.Picasso


class ArtistAdapter (): RecyclerView.Adapter<ArtistAdapter.ArtistHolder>(){
    private var artists : List<Artist>? = null

    override fun onBindViewHolder(holder: ArtistHolder, position: Int) {
        val item = artists?.get(position)
        holder.bind(item!!)
    }

    fun setAlbums(artistsList: List<Artist>) {
        this.artists = artistsList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ArtistHolder(layoutInflater.inflate(R.layout.item_artist, parent, false))
    }

    override fun getItemCount(): Int {
        if(artists == null) return 0
        else return artists?.size!!
    }

    class ArtistHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemArtistBinding.bind(view)

        fun bind(artist: Artist){
            binding.tvArtistName.text = artist.name
            binding.tvArtistDescription.text = artist.description
            Picasso.get().load(artist.image).into(binding.ivArtistImage)
            //itemView.setOnClickListener(View.OnClickListener { Toast.makeText(context, "Clicked on $artist.name", Toast.LENGTH_SHORT).show() })
        }
    }


}