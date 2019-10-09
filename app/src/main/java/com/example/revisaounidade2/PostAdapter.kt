package com.example.revisaounidade4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.revisaounidade2.Post
import com.example.revisaounidade2.R

class PostAdapter(val postList: ArrayList<Post>, val longClickListener: (Int) -> Unit) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = postList[position]

        holder.bind(position, this, longClickListener)
        holder.idTextView.text = post.id.toString()
        holder.titleTextView.text = post.title
        holder.contentTextView.text = post.content
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idTextView: TextView = itemView.findViewById<TextView>(R.id.txt_post_id)
        val titleTextView: TextView = itemView.findViewById<TextView>(R.id.txt_post_title)
        val contentTextView: TextView = itemView.findViewById<TextView>(R.id.txt_post_content)

        fun bind(position: Int, adapter: PostAdapter, longClickListener: (Int) -> Unit) = with(itemView){
            val postView = itemView.findViewById<CardView>(R.id.card_view)
            postView.setOnLongClickListener {
                longClickListener(position)
                adapter.notifyDataSetChanged()

                true
            }
        }
    }
}