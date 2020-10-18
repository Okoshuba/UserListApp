package com.example.testtask.Network

import com.example.testtask.Repository.Posts
import com.example.testtask.Repository.Users
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    interface UsersAPI {
        @GET("/users")
        fun getUsers(): Call<List<Users>>
    }

    interface PostsAPI {
        @GET("/posts")
        fun getPosts(): Call<List<Posts>>
    }
}