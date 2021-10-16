package com.example.films.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.films.R
import com.example.films.databinding.DetailFragmentBinding
import timber.log.Timber

class DetailFragment: Fragment(R.layout.detail_fragment) {

    private val filmDetailViewModel by viewModels<FilmDetailViewModel> {
        FilmDetailViewModelFactory()
    }

    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!

    val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = DetailFragmentBinding.bind(view)

        val currentFilmId = args.filmId

        currentFilmId.let {
            val currentFilm = filmDetailViewModel.getFilmForId(it)
            Timber.i(currentFilm.toString())
            binding.filmTitleTextView.text = currentFilm?.title
            Glide.with(view)
                .load(currentFilm?.poster_path)
                .centerCrop()
                .into(binding.filmPosterImageView)
            binding.filmDateRelease.text = currentFilm?.release_date

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}