package com.example.latihanretrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecycleAdapter(val list:ArrayList<AlbumsItem>, val context: Context):RecyclerView.Adapter<RecycleAdapter.ViewHolder>() {
    class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){
        val userId:TextView=itemView.findViewById(R.id.tvUser)

        val id :TextView=itemView.findViewById(R.id.tvId)

        val title:TextView=itemView.findViewById(R.id.tvTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_post,parent,false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem= list[position]

        holder.apply {
            userId.text= currentItem.userId.toString()
            id.text=currentItem.id.toString()
            title.text=currentItem.title
        }
    }
}