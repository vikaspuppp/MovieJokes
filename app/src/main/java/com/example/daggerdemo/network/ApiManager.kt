package com.example.daggerdemo.network

import android.util.Log
import com.example.daggerdemo.callback.ApiCallBacks
import com.example.daggerdemo.model.JokeModel
import com.example.daggerdemo.model.MovieModel
import retrofit2.*
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class ApiManager(var apiCallBacks: ApiCallBacks) {
    val jokeRetrofit: Retrofit
    val jokeCall: Call<List<JokeModel>>
    val movieRetrofit: Retrofit
    val movieCall: Call<List<MovieModel>>

    init {
        jokeRetrofit = Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ApiUrl.JOKE_BASE_URL).build()
        jokeCall = jokeRetrofit.create<JokeApi>().getJoke()
        movieRetrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ApiUrl.MOVIE_BASE_URL).build()
        movieCall = movieRetrofit.create<MovieApi>().getMovie()
    }

    fun getJokes() {
        apiCallBacks.showProgressDialog()
        jokeCall.enqueue(object : Callback<List<JokeModel>> {
            override fun onFailure(call: Call<List<JokeModel>>, t: Throwable) {
                Log.d("response", "fail")
                apiCallBacks.hideProgressDialog()
            }

            override fun onResponse(
                call: Call<List<JokeModel>>,
                response: Response<List<JokeModel>>
            ) {
                Log.d("response", "success")
                apiCallBacks.hideProgressDialog()
                response.body()?.let { apiCallBacks.addData(it) }
            }
        })
    }

    fun getMovie() {
        apiCallBacks.showProgressDialog()
        movieCall.enqueue(object : Callback<List<MovieModel>> {
            override fun onFailure(call: Call<List<MovieModel>>, t: Throwable) {
                Log.d("response", "fail")
                apiCallBacks.hideProgressDialog()
            }

            override fun onResponse(
                call: Call<List<MovieModel>>,
                response: Response<List<MovieModel>>
            ) {
                Log.d("response", "success")
                apiCallBacks.hideProgressDialog()
                response.body()?.let { apiCallBacks.addData(it) }
            }
        })
    }

}