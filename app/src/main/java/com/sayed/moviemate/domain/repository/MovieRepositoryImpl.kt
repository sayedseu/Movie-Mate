package com.sayed.moviemate.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sayed.moviemate.data.network.ApiService
import com.sayed.moviemate.data.source.MoviePagingSource
import com.sayed.moviemate.domain.model.Movie
import com.sayed.moviemate.domain.model.MoviesResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    MovieRepository {

    override fun getMovies(): Flow<PagingData<Movie>> {
        return Pager(
            PagingConfig(pageSize = 40, enablePlaceholders = false, prefetchDistance = 3),
            pagingSourceFactory = { MoviePagingSource(apiService = apiService) }
        ).flow
    }
}