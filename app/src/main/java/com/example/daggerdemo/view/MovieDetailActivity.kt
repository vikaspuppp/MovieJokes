package com.example.daggerdemo.view

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.method.ScrollingMovementMethod
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.daggerdemo.R
import com.example.daggerdemo.model.MovieModel
import com.example.daggerdemo.viewmodel.MovieDetailViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {
    private val viewMovieModel: MovieDetailViewModel by viewModels()
    @TargetApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        setData()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun setData() {
        viewMovieModel.getMovieData().observe(this,
            Observer<MovieModel> {
                setAnswer(it)
            })

    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun setAnswer(model: MovieModel) {
        tv_name.text = model.name
        tv_first_appearence.text =
            "Published by ${model.publisher} in ${model.firstAppearance} as team of ${model.team} created by ${model.createdBy}"
        tv_bio.text = Html.fromHtml(model.bio, Html.FROM_HTML_OPTION_USE_CSS_COLORS)
        Picasso.get().load(model.imageUrl).into(iv_movie_pic)
        tv_bio.movementMethod = ScrollingMovementMethod()
    }
}
