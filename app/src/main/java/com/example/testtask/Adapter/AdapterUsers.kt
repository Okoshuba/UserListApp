package com.example.testtask.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.Listener.Listener
import com.example.testtask.Repository.Users
import kotlinx.android.synthetic.main.item_recycler_view.view.emailUser
import kotlinx.android.synthetic.main.item_recycler_view.view.phoneUser
import kotlinx.android.synthetic.main.item_recycler_view.view.website

class AdapterUsers(private val context: Context,
                   private val users: List<Users>,
                   val callback: Listener) : RecyclerView.Adapter<AdapterUsers.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(com.example.testtask.R.layout.item_recycler_view,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]

        holder.firstname.text = user.name
        holder.emailUser.text = user.email
        holder.phoneUser.text = user.phone
        holder.websiteUser.text = user.website


        holder.itemView.setOnClickListener {callback.onItemClickListener(position, holder.firstname)}

    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

        val firstname: TextView = itemView.findViewById(com.example.testtask.R.id.nameUser)
        val emailUser: TextView = itemView.emailUser
        val phoneUser: TextView = itemView.phoneUser
        val websiteUser: TextView = itemView.website

        //val innerRecyclerView: RecyclerView = itemView.postsRecyclerView

    }
}
