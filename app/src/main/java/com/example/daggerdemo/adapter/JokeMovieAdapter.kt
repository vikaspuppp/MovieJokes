package com.example.daggerdemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.daggerdemo.R
import com.example.daggerdemo.adapter.JokeMovieAdapter.Holder
import com.example.daggerdemo.model.JokeModel
import com.example.daggerdemo.model.MovieModel
import com.squareup.picasso.Picasso

class JokeMovieAdapter(
    private val context: Context,
    private val list: List<Any>,
    private val listener: AdapterListener
) :
    RecyclerView.Adapter<Holder>() {
    enum class ViewType {
        MOVIE, JOKE
    }

    override fun getItemViewType(position: Int): Int {
        return when (list[position]) {
            is JokeModel -> ViewType.JOKE.ordinal
            else -> ViewType.MOVIE.ordinal
        }
    }

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(context)

        return when (viewType) {
            ViewType.MOVIE.ordinal -> {
                MovieHolder(inflater.inflate(R.layout.rv_movie_cell, parent, false), listener)
            }
            else -> {
                JokeHolder(inflater.inflate(R.layout.rv_joke_cell, parent, false), listener)
            }
        }
    }


    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind(list[position])


    class JokeHolder(itemView: View, private val listener: AdapterListener) : Holder(itemView),
        View.OnClickListener {
        private var tvJoke: TextView = itemView.findViewById(R.id.tv_cell_ques)
        private lateinit var model: JokeModel

        init {
            itemView.setOnClickListener(this)
        }

        override fun bind(any: Any) {
            model = any as JokeModel
            tvJoke.text = model.qus
        }

        override fun onClick(v: View?) {
            listener.onClick(model)
        }

    }

    class MovieHolder(itemView: View, private val listener: AdapterListener) : Holder(itemView),
        View.OnClickListener {
        private var tvMovieName: TextView = itemView.findViewById(R.id.tv_movie_name)
        private var tvReleaseDate: TextView = itemView.findViewById(R.id.tv_release_date)
        private var ivMovie: ImageView = itemView.findViewById(R.id.iv_image)
        private lateinit var model: MovieModel

        init {
            itemView.setOnClickListener(this)
        }

        override fun bind(any: Any) {

            model = any as MovieModel
            tvReleaseDate.text = model.firstAppearance
            tvMovieName.text = model.name
            Picasso.get().load(model.imageUrl).into(ivMovie)
        }

        override fun onClick(v: View?) {
            listener.onClick(model)
        }

    }

    abstract class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(any: Any)
    }

   public  interface AdapterListener {
        fun onClick(any: Any)
    }

}