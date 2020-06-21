package com.example.daggerdemo.dagger.component

import com.example.daggerdemo.dagger.module.ApiCallbacksModule
import com.example.daggerdemo.dagger.module.ApiManagerModule
import com.example.daggerdemo.view.MainActivity
import dagger.Component

@Component(modules = [ApiManagerModule::class])
interface ApiManagerComponent {


}