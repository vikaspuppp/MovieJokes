package com.example.daggerdemo.dagger.module

import com.example.daggerdemo.callback.ApiCallBacks
import com.example.daggerdemo.network.ApiManager
import dagger.Module
import dagger.Provides

@Module
class ApiManagerModule {

    @Provides
    fun provideApiManager(callbacks: ApiCallBacks): ApiManager = ApiManager(callbacks)

    @Provides
    fun provideDataList(): ArrayList<Any> = arrayListOf()
}