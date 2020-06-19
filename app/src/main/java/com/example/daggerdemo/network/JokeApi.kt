package com.example.daggerdemo.network

import com.example.daggerdemo.model.JokeModel
import com.example.daggerdemo.model.MovieModel
import retrofit2.Call
import retrofit2.http.GET

interface JokeApi {
    @GET("joke")
    fun getJoke(): Call<List<JokeModel>>
}