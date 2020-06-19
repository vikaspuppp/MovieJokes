package com.example.daggerdemo.model

import com.google.gson.annotations.SerializedName

data class JokeModel(@SerializedName("qus") val qus: String, @SerializedName("ans") val ans: String) {
}
