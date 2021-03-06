package com.example.vynilos.views.adapters


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vynilos.R
import com.example.vynilos.databinding.ItemCollectorBinding
import com.example.vynilos.models.Collector
import com.example.vynilos.views.CollectorDetailActivity

class CollectorAdapter : RecyclerView.Adapter<CollectorAdapter.CollectorHolder>(){
    private var collectors : List<Collector>? = null

    override fun onBindViewHolder(holder: CollectorHolder, position: Int) {
        val item = collectors?.get(position)
        holder.bind(item!!)
    }

    fun setCollectors(collectorsList: List<Collector>) {
        this.collectors = collectorsList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectorHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CollectorHolder(layoutInflater.inflate(R.layout.item_collector, parent, false))
    }

    override fun getItemCount(): Int {
        return if(collectors == null) 0
        else collectors?.size!!
    }

    class CollectorHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemCollectorBinding.bind(view)

        fun bind(collector: Collector){
            binding.tvCollectorName.text = collector.name
            binding.tvTelephone.text = collector.telephone
            binding.tvEmail.text = collector.email

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, CollectorDetailActivity::class.java)
                intent.putExtra("collectorId", collector.id.toString())
                itemView.context.startActivity(intent)
            }
        }
    }


}