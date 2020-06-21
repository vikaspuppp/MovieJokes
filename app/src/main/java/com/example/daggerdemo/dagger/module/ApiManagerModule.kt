package com.example.daggerdemo.dagger.module

import com.example.daggerdemo.callback.ApiCallBacks
import com.example.daggerdemo.network.ApiManager
import dagger.Module
import dagger.Provides

@Module
class ApiManagerModule constructor(var apiCallBacks: ApiCallBacks) {

    @Provides
    fun provideApiManager(): ApiManager = ApiManager(apiCallBacks)

    @Provides
    fun provideApiCallBacks(): ApiCallBacks = apiCallBacks
}