package com.sayed.moviemate.data.source

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sayed.moviemate.data.network.ApiService
import com.sayed.moviemate.domain.model.Movie
import com.sayed.moviemate.domain.model.MoviesResponse
import retrofit2.HttpException
import java.io.IOException

const val STARTING_PAGE_INDEX = 1


class MoviePagingSource(private val apiService: ApiService) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = apiService.getMovies(page)
            Log.d("hasan in source", "load: $response")
            LoadResult.Page(
                data = response.results,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (response.results.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}