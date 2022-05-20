package com.sayed.moviemate.view.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sayed.moviemate.R
import com.sayed.moviemate.domain.model.Movie
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class MovieAdapter @Inject constructor(@ActivityContext private val context: Context) :
    PagingDataAdapter<Movie, MovieAdapter.MovieViewHolder>(DiffUtilCallBack()) {

    private lateinit var listener: OnMovieItemClickListener

    fun setCallbackListener(listener: OnMovieItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view,listener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { holder.bindPost(it) }
    }

    class MovieViewHolder(itemView: View,private val listener: OnMovieItemClickListener) : RecyclerView.ViewHolder(itemView) {
        private val titleTV: TextView = itemView.findViewById(R.id.titleTV)
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)
        private val item: View = itemView.findViewById(R.id.itemView)

        fun bindPost(movie: Movie) {
            with(movie) {
                titleTV.text = title
                imageView.load("https://image.tmdb.org/t/p/w185/$poster_path")
                item.setOnClickListener{
                    listener.onClick(this)
                }
            }
        }
    }
}

class DiffUtilCallBack : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }
}

interface OnMovieItemClickListener {
    fun onClick(data: Movie)
}