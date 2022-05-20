package com.sayed.moviemate.view.moview_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import com.sayed.moviemate.databinding.FragmentMovieDetailsBinding

class MovieDetailsFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var binding: FragmentMovieDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchMovie()
    }

    private fun fetchMovie() {
        sharedViewModel.movie.observe(viewLifecycleOwner) {
            binding.moviePoster.load("https://image.tmdb.org/t/p/w185/${it.poster_path}")
            binding.title.text = it?.original_title
            binding.vote.text = "${it.vote_average}(${it.vote_count})"
            binding.overview.text = it?.overview
        }
    }
}