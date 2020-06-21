package com.example.daggerdemo.dagger.component

import com.example.daggerdemo.dagger.module.ApiCallbacksModule
import com.example.daggerdemo.dagger.module.ApiManagerModule
import com.example.daggerdemo.dagger.module.JokeMovieAdapterModule
import com.example.daggerdemo.view.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(/*dependencies = [ApiManagerComponent::class],*/ modules = [JokeMovieAdapterModule::class,ApiManagerModule::class])
interface AdapterComponent {

    fun inject(mainActivity: MainActivity)
}