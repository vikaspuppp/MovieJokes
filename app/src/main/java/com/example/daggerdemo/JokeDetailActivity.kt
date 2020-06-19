package com.example.daggerdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.daggerdemo.model.DataTransfer
import com.example.daggerdemo.model.JokeModel
import kotlinx.android.synthetic.main.activity_joke_detail.*

class JokeDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_joke_detail)
        tv_answer.text = (DataTransfer.get() as JokeModel).ans
    }
}
