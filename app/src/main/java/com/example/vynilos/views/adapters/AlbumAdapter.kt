package com.example.vynilos.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vynilos.R
import com.example.vynilos.databinding.ItemAlbumBinding
import com.example.vynilos.models.Album
import com.squareup.picasso.Picasso

class AlbumAdapter() : RecyclerView.Adapter<AlbumAdapter.AlbumHolder>() {
    private var albums : List<Album>? = null

    override fun onBindViewHolder(holder: AlbumHolder, position: Int) {
        val item = albums?.get(position)
        holder.bind(item!!)
    }

    fun setAlbums(albumsList: List<Album>) {
        this.albums = albumsList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AlbumHolder(layoutInflater.inflate(R.layout.item_album, parent, false))
    }

    override fun getItemCount(): Int {
        if(albums == null) return 0
        else return albums?.size!!
    }

    class AlbumHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemAlbumBinding.bind(view)

        fun bind(album:Album){
            binding.tvName.text = album.name
            binding.tvDescription.text = album.description
            binding.tvGenre.text = album.genre
            binding.tvRecordLabel.text = album.recordLabel
            Picasso.get().load(album.cover).into(binding.ivCover)
            //itemView.setOnClickListener(View.OnClickListener { Toast.makeText(context, "Clicked on $album.name", Toast.LENGTH_SHORT).show() })
        }
    }
}