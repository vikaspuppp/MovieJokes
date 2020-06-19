package com.example.daggerdemo.callback

interface ApiCallBacks {

    fun showProgressDialog()

    fun hideProgressDialog()

    fun addData(call: List<Any>)
}