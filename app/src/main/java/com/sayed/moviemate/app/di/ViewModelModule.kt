package com.sayed.moviemate.app.di

import com.sayed.moviemate.domain.repository.MovieRepository
import com.sayed.moviemate.domain.repository.MovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {

    @Binds
    abstract fun bindMovieRepository(movieRepository: MovieRepositoryImpl) : MovieRepository
}