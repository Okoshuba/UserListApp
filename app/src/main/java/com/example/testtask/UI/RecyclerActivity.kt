package com.example.testtask.UI

import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testtask.Adapter.AdapterUsers
import com.example.testtask.Listener.Listener
import com.example.testtask.Network.RetrofitBuilder
import com.example.testtask.Repository.Users
import kotlinx.android.synthetic.main.recycler_view_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.Adapter.PostsAdapter
import com.example.testtask.Repository.Posts
import kotlinx.android.synthetic.main.item_recycler_view.*


class RecyclerActivity: AppCompatActivity(), Listener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.testtask.R.layout.recycler_view_layout)

        fetchData()
    }

    private fun fetchData() {
        RetrofitBuilder.newApi.getUsers().enqueue(object: Callback<List<Users>>{
            override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {

                showData(response.body()!!)
                progressBar.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE

                //d("Check", "onResponse: ${response.body()!![0].name}")
            }

            override fun onFailure(call: Call<List<Users>>, t: Throwable) {
                d("Check", "onFailure: ${t.message}")
                toRefreshActivity()
            }
        })
    }

    //Метод, который формирует список пользователей в recyclerView
    private fun showData(users: List<Users>) {
        val recyclerView: RecyclerView = findViewById(com.example.testtask.R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = AdapterUsers(this, users, this)
    }

    //Обработчик нажатия на элемент коллекции
    override fun onItemClickListener(position: Int) {

        fetchPosts()

        val isShown = postsRecyclerView.visibility == View.VISIBLE
        postsRecyclerView.visibility = if (isShown) View.GONE else View.VISIBLE
    }

    //Переход на активити перезагрузки данных из-за ошибки
    fun toRefreshActivity(){
        val intent = Intent(this, RefreshActivity::class.java)
        startActivity(intent)
    }

    private fun fetchPosts() {
        RetrofitBuilder.newApiPost.getPosts().enqueue(object: Callback<List<Posts>> {

            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {

                showPost(response.body()!!)
                //d("Check", "onResponse: ${response.body()!![0].title}")
            }
            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
                //d("Check", "onFailure: ${t.message}")
                val text = "Data downloader error. Please check the Internet"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
            }
        })
    }

    //Метод, который формирует список постов пользователей в recyclerView
    private fun showPost (posts: List<Posts>) {
        postsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@RecyclerActivity)
            adapter = PostsAdapter(posts)
        }
    }
}