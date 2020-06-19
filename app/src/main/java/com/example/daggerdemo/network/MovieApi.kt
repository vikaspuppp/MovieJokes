package com.example.daggerdemo.network

import com.example.daggerdemo.model.MovieModel
import retrofit2.Call
import retrofit2.http.GET

interface MovieApi {
    @GET("marvel")
    fun getMovie(): Call<List<MovieModel>>
}