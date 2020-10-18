package com.example.testtask.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.R
import com.example.testtask.Repository.Posts
import kotlinx.android.synthetic.main.item_rv_posts.view.*

class PostsAdapter(private val posts: List<Posts>): RecyclerView.Adapter<PostsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_posts, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = posts[position]

        holder.titlePost.text = post.title
        holder.bodyTitle.text = post.body
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titlePost: TextView = itemView.textTitle
        val bodyTitle: TextView = itemView.textMain
    }
}