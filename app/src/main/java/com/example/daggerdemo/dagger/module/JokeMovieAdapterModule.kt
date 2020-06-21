package com.example.daggerdemo.dagger.module

import android.content.Context
import com.example.daggerdemo.adapter.JokeMovieAdapter
import com.example.daggerdemo.adapter.JokeMovieAdapter.AdapterListener
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class JokeMovieAdapterModule(var context: Context, var listener: AdapterListener) {

    @Provides
    @Singleton
    fun provideDataList(): ArrayList<Any> = arrayListOf()

    @Provides
    fun provideContext(): Context = context

    @Provides
    fun provideAdapterListener(): AdapterListener = listener


    @Provides
    fun provideJokeMovieAdapter(
        context: Context,
        dataList: ArrayList<Any>,
        listener: AdapterListener
    ): JokeMovieAdapter =
        JokeMovieAdapter(context, dataList, listener)

}