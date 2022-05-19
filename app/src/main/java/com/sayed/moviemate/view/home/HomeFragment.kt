package com.sayed.moviemate.view.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.sayed.moviemate.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private val adapter: MovieAdapter = MovieAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rec:RecyclerView = view.findViewById(R.id.rvMovies)
        rec.adapter = adapter
        fetchMovies()
    }
    private fun fetchMovies() {
        lifecycleScope.launch {
            viewModel.fetchMovies().collectLatest { pagingData ->
                Log.d("Hasan", "fetchMovies: ${pagingData}")
                adapter.submitData(pagingData)
            }
        }

    }


}