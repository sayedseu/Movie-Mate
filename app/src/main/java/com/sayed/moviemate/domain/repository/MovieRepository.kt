package com.sayed.moviemate.domain.repository

import androidx.paging.PagingData
import com.sayed.moviemate.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies() : Flow<PagingData<Movie>>
}