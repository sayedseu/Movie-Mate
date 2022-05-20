package com.sayed.moviemate.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.sayed.moviemate.R
import com.sayed.moviemate.databinding.FragmentHomeBinding
import com.sayed.moviemate.domain.model.Movie
import com.sayed.moviemate.view.moview_details.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(), OnMovieItemClickListener {
    @Inject
    lateinit var adapter: MovieAdapter
    private val viewModel: HomeViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        adapter.setCallbackListener(this)
        binding.rvMovies.adapter = adapter
        fetchMovies()
    }

    private fun fetchMovies() {
        lifecycleScope.launch {
            viewModel.fetchMovies().collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }

    }

    override fun onClick(data: Movie) {
        sharedViewModel.setMovie(data)
        navController.navigate(R.id.action_homeFragment_to_movieDetailsFragment)
    }

}