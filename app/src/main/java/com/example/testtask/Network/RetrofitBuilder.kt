package com.example.testtask.Network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    val retrofit = Retrofit.Builder()
        .baseUrl(BaseUrl.baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val newApi = retrofit.create(Api.UsersAPI::class.java)
    val newApiPost = retrofit.create(Api.PostsAPI::class.java)
}