package com.example.films.ui.detail

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.CircleCropTransformation
import com.example.films.AppState
import com.example.films.R
import com.example.films.databinding.DetailFragmentBinding
import com.example.films.model.entities.Film

class DetailFragment : Fragment(R.layout.detail_fragment) {

    private val viewModel: DetailsViewModel by lazy {
        ViewModelProvider(this).get(DetailsViewModel::class.java)
    }


    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = DetailFragmentBinding.bind(view)

        val currentFilmId = args.filmId

        viewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)
        }

        viewModel.getFilmFromRemoteSource(currentFilmId)
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                showFilm(appState.filmData[0], view)
            }
            is AppState.Loading -> {
            }
            is AppState.Error -> {
            }
        }
    }

    private fun showFilm(film: Film, view: View?) {
        binding.filmTitleTextView.text = film.title
        if (view != null) {
            binding.filmPosterImageView
                .load("https://image.tmdb.org/t/p/w500/${film.poster_path}") {
                    crossfade(true)
                    placeholder(R.drawable.movie_placeholder)
                    transformations(CircleCropTransformation())
                }
        }

        binding.filmDateRelease.text = film.release_date
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
