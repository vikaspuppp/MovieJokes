package com.example.daggerdemo.network

import android.util.Log
import com.example.daggerdemo.callback.ApiCallBacks
import com.example.daggerdemo.model.JokeModel
import com.example.daggerdemo.model.MovieModel
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ApiManager(var apiCallBacks: ApiCallBacks) {
    private val jokeRetrofit: Observable<List<JokeModel>> by lazy {
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ApiUrl.JOKE_BASE_URL).build().create<JokeApi>().getJoke()
    }
    private val movieRetrofit: Observable<List<MovieModel>> by lazy {
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ApiUrl.MOVIE_BASE_URL).build().create<MovieApi>().getMovie()
    }


    fun getJokes() {
        apiCallBacks.showProgressDialog()
        jokeRetrofit.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<JokeModel>> {
                override fun onComplete() {
                    apiCallBacks.hideProgressDialog()
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(value: List<JokeModel>) {
                    Log.d("response", "JOKE API success")
                    value.let { apiCallBacks.addData(it) }
                }

                override fun onError(e: Throwable) {
                    Log.d("fail", "joke api fail:  ${e.localizedMessage}")
                    apiCallBacks.hideProgressDialog()
                }
            })
    }

    fun getMovie() {
        apiCallBacks.showProgressDialog()
        movieRetrofit.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<MovieModel>> {
                override fun onComplete() {
                    apiCallBacks.hideProgressDialog()
                }

                override fun onSubscribe(d: Disposable) {}

                override fun onNext(value: List<MovieModel>) {
                    Log.d("response", "Movie api success")
                    value.let { apiCallBacks.addData(it) }
                }

                override fun onError(e: Throwable) {
                    Log.d("response", "Movie api fail: ${e.localizedMessage}")
                    apiCallBacks.hideProgressDialog()
                }
            })
    }

}