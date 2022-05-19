package com.sayed.moviemate.view.home

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sayed.moviemate.R
import com.sayed.moviemate.domain.model.Movie

class MovieAdapter : PagingDataAdapter<Movie, MovieAdapter.MovieViewHolder>(DiffUtilCallBack()) {

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val titleTV :TextView = itemView.findViewById(R.id.textView)

        fun bindPost(movie: Movie) {
            with(movie) {
                titleTV.text = title
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { holder.bindPost(it) }
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