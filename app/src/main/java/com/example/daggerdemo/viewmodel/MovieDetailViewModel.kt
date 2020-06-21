package com.example.daggerdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.daggerdemo.model.DataTransfer
import com.example.daggerdemo.model.MovieModel

class MovieDetailViewModel : ViewModel() {

    private val movieData: MutableLiveData<MovieModel> by lazy { loadData() }

    private fun loadData(): MutableLiveData<MovieModel> =
        MutableLiveData<MovieModel>().also { it.value = DataTransfer.get() as MovieModel }

    fun getMovieData(): LiveData<MovieModel> = movieData
}