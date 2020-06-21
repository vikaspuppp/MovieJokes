package com.example.daggerdemo.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.daggerdemo.R
import com.example.daggerdemo.model.JokeModel
import com.example.daggerdemo.viewmodel.JokeDetailViewModel
import kotlinx.android.synthetic.main.activity_joke_detail.*

class JokeDetailActivity : AppCompatActivity() {
    private val viewJokeModel: JokeDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_joke_detail)
        setData()

    }

    private fun setData() {
        viewJokeModel.getJokeData().observe(this,
            Observer<JokeModel> {
                setAnswer(it)
            })

    }

    private fun setAnswer(it: JokeModel?) {
        tv_answer.text = it?.ans
    }
}
