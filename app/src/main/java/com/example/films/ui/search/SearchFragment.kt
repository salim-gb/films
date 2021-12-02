package com.example.films.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.films.AppState
import com.example.films.R
import com.example.films.databinding.SearchFragmentBinding

class SearchFragment : Fragment(R.layout.search_fragment) {

    private val viewModel: SearchViewModel by lazy {
        ViewModelProvider(this).get(SearchViewModel::class.java)
    }

    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = SearchFragmentBinding.bind(view)

        binding.searchTextview.setOnClickListener {
            findNavController().navigate(R.id.action_search_to_sub_search, null)
        }

        viewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)
        }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {

            }
        }
    }
}