package com.example.daggerdemo.dagger.module

import com.example.daggerdemo.callback.ApiCallBacks
import com.example.daggerdemo.view.MainActivity
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
 class ApiCallbacksModule(var apiCallbacks :ApiCallBacks) {

    @Provides
     fun providesApiCallBacks(): ApiCallBacks =apiCallbacks
}