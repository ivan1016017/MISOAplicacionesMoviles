package com.example.vynilos.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.vynilos.R
import com.example.vynilos.models.Album
import com.squareup.picasso.Picasso

class AlbumAdapter : RecyclerView.Adapter<AlbumAdapter.AlbumHolder>() {
    var albums: List<Album>  = ArrayList()
    lateinit var context: Context

    fun AlbumAdapter(albums : List<Album>, context: Context){
        this.albums = albums
        this.context = context
    }

    override fun onBindViewHolder(holder: AlbumHolder, position: Int) {
        val item = albums.get(position)
        holder.bind(item, context)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AlbumHolder(layoutInflater.inflate(R.layout.item_album, parent, false))
    }
    override fun getItemCount(): Int {
        return albums.size
    }
    class AlbumHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById(R.id.tvName) as TextView
        val description = view.findViewById(R.id.tvDescription) as TextView
        val genre = view.findViewById(R.id.tvGenre) as TextView
        val recordLabel = view.findViewById(R.id.tvRecordLabel) as TextView
        val cover = view.findViewById(R.id.ivCover) as ImageView
        fun bind(album:Album, context: Context){
            name.text = album.name
            description.text = album.description
            genre.text = album.genre
            recordLabel.text = album.recordLabel
            Picasso.get().load(album.cover).into(cover)
            itemView.setOnClickListener(View.OnClickListener { Toast.makeText(context, "Clicked on $album.name", Toast.LENGTH_SHORT).show() })
        }
    }
}