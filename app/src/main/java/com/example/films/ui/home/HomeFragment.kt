package com.example.films.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.films.R
import com.example.films.data.Film
import com.example.films.databinding.HomeFragmentBinding

class HomeFragment : Fragment(R.layout.home_fragment) {
    private val viewModel by viewModels<HomeViewModel> {
        HomeViewModelFactory()
    }

    private lateinit var filmsAdapter: FilmsAdapter

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        filmsAdapter = FilmsAdapter { film ->
            adapterOnClick(film)
        }
    }

    private fun adapterOnClick(film: Film) {
        val action = HomeFragmentDirections.actionHomeToDetail(film.id)
        findNavController().navigate(action)
//        Toast.makeText(context, "Film: title ${film.title}", Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = HomeFragmentBinding.bind(view)

        binding.homeRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = filmsAdapter
        }

        viewModel.filmsLiveData.observe(viewLifecycleOwner) {
            it?.let {
                filmsAdapter.submitList(it as MutableList<Film>)
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