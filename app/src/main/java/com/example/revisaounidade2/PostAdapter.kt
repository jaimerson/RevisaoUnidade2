package com.example.revisaounidade4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class PostAdapter(val postList: ArrayList<Post>, val longClickListener: (Int) -> Unit) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position, longClickListener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(position: Int, longClickListener: (Int) -> Unit){
            val postView = itemView.findViewById<CardView>(R.id.recycler_view)
            postView.setOnLongClickListener {
                longClickListener(position)
                true
            }
        }
    }
}