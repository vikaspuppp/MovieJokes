package com.example.daggerdemo.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.daggerdemo.R
import com.example.daggerdemo.adapter.JokeMovieAdapter
import com.example.daggerdemo.callback.ApiCallBacks
import com.example.daggerdemo.dagger.component.DaggerAdapterComponent
import com.example.daggerdemo.dagger.module.ApiCallbacksModule
import com.example.daggerdemo.dagger.module.ApiManagerModule
import com.example.daggerdemo.dagger.module.JokeMovieAdapterModule
import com.example.daggerdemo.model.DataTransfer
import com.example.daggerdemo.model.JokeModel
import com.example.daggerdemo.network.ApiManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ApiCallBacks, JokeMovieAdapter.AdapterListener {

    lateinit var rvJokeMovie: RecyclerView
    @Inject
    lateinit var dataList: ArrayList<Any>
    @Inject
    lateinit var adapter: JokeMovieAdapter
    @Inject
    lateinit var manager: ApiManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        DaggerAdapterComponent.builder()
            .apiManagerModule(ApiManagerModule(this))
            .jokeMovieAdapterModule(
                JokeMovieAdapterModule(this, this)
            ).build()
            .inject(this)
        init()
        setAdapter()
        hitApiCall()
    }

    private fun hitApiCall() {
        manager.getJokes()
        manager.getMovie()
    }

    /*Set adapter at demo mode then notify data when data is available from server*/
    private fun setAdapter() {
        rvJokeMovie.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvJokeMovie.adapter = adapter
    }

    fun init() {
        rvJokeMovie = findViewById(R.id.rv_jokes_movies)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showProgressDialog() {

        if (!pb_data_call.isVisible) {
            rvJokeMovie.visibility = View.GONE
            pb_data_call.visibility = View.VISIBLE
        }
    }

    override fun hideProgressDialog() {
        if (pb_data_call.isVisible) {
            rvJokeMovie.visibility = View.VISIBLE
            pb_data_call.visibility = View.GONE
        }
    }

    override fun addData(call: List<Any>) {
        dataList.addAll(call)
        adapter.notifyDataSetChanged()
    }

    override fun onClick(any: Any) {
        val intent: Intent = when (any) {
            is JokeModel -> Intent(this, JokeDetailActivity::class.java)
            else -> Intent(this, MovieDetailActivity::class.java)
        }
        DataTransfer.set(any)
        startActivity(intent)
    }


}
