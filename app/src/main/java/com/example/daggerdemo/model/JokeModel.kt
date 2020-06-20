package com.example.daggerdemo.model

import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class JokeModel @Inject constructor(@SerializedName("qus") val qus: String, @SerializedName("ans") val ans: String) {
}
