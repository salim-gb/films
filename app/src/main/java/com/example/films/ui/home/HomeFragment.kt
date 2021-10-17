package com.example.films.ui.home

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.films.AppState
import com.example.films.R
import com.example.films.databinding.HomeFragmentBinding
import com.example.films.model.entities.Film
import com.example.films.showSnackBar
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.home_fragment) {
    private val viewModel: HomeViewModel by viewModel()
    private var filmsAdapter: FilmsAdapter? = null

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    private fun adapterOnClick(film: Film) {
        val action = film.id?.let { HomeFragmentDirections.actionHomeToDetail(it) }
        if (action != null) {
            findNavController().navigate(action)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = HomeFragmentBinding.bind(view)

        viewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)
        }
        viewModel.getFilmsData()

        binding.homeRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = filmsAdapter
        }
    }

    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.Success -> {
                homeFragmentLoading.visibility = View.GONE
                homeFragmentLoadingFail.visibility = View.GONE
                filmsAdapter = FilmsAdapter { film ->
                    adapterOnClick(film)
                }.apply {
                    submitList(appState.filmData as MutableList<Film>)
                }
                binding.homeRecyclerView.adapter = filmsAdapter
            }
            is AppState.Loading -> {
                homeFragmentLoading.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                homeFragmentLoading.visibility = View.GONE
                homeFragmentLoadingFail.visibility = View.VISIBLE
                binding.root.showSnackBar(appState.error.toString())
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}