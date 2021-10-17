package com.example.films.ui.detail

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.films.R
import com.example.films.databinding.DetailFragmentBinding
import com.example.films.model.entities.Film
import timber.log.Timber

class DetailFragment : Fragment(R.layout.detail_fragment) {

    private val filmDetailViewModel: FilmDetailViewModel by viewModels()

    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!

    val args: DetailFragmentArgs by navArgs()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = DetailFragmentBinding.bind(view)

        val currentFilmId = args.filmId
        Timber.i("film id **** $currentFilmId")


        filmDetailViewModel.liveData.observe(viewLifecycleOwner) {
            showFilm(it, view)
        }

        filmDetailViewModel.getFilmForId(currentFilmId)
    }

    private fun showFilm(film: Film, view: View) {
        binding.filmTitleTextView.text = film.title
        Glide.with(view)
            .load("https://image.tmdb.org/t/p/w500/${film.poster_path}")
            .centerCrop()
            .into(binding.filmPosterImageView)
        binding.filmDateRelease.text = film.release_date
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}