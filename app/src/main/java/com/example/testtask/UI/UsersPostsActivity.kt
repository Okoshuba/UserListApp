package com.example.testtask.UI

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testtask.Adapter.PostsAdapter
import com.example.testtask.Listener.Listener
import com.example.testtask.Network.RetrofitBuilder
import com.example.testtask.Repository.Posts
import com.example.testtask.Repository.Users
import kotlinx.android.synthetic.main.users_posts_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersPostsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.testtask.R.layout.users_posts_layout)

        fetchPosts()
    }

    //Получение постов юзеров с JSON через ретрофит билдер. Метод реализован в отдельном объект-классе
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
            layoutManager = LinearLayoutManager(this@UsersPostsActivity)
            adapter = PostsAdapter(posts)
        }
    }
}