package com.example.daggerdemo.network

import com.example.daggerdemo.model.MovieModel
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface MovieApi {
    @GET("marvel")
    fun getMovie(): Observable<List<MovieModel>>
}