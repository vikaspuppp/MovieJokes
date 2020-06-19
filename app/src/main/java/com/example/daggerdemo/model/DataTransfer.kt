package com.example.daggerdemo.model

object DataTransfer {
    private var data: Any? = null

    fun set(data: Any) {
        this.data = data
    }

    fun get(): Any? {
        val local: Any? = data
        data = null
        return local
    }
}