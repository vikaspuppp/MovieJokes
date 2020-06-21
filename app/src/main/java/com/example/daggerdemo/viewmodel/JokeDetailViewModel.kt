package com.example.daggerdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.daggerdemo.model.DataTransfer
import com.example.daggerdemo.model.JokeModel

class JokeDetailViewModel : ViewModel() {

    private val jokeData: MutableLiveData<JokeModel> by lazy { loadData() }

    private fun loadData(): MutableLiveData<JokeModel> =
        MutableLiveData<JokeModel>().also { it.value = DataTransfer.get() as JokeModel }

    fun getJokeData(): LiveData<JokeModel> = jokeData
}

