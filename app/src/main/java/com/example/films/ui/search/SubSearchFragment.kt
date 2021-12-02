package com.example.films.ui.search

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.films.AppState
import com.example.films.R
import com.example.films.databinding.SubSearchFragmentBinding
import com.example.films.model.entities.Film
import com.example.films.ui.home.FilmsAdapter


class SubSearchFragment : Fragment(R.layout.sub_search_fragment) {
    private val viewModel: SearchViewModel by lazy {
        ViewModelProvider(this).get(SearchViewModel::class.java)
    }

    private var filmsAdapter: FilmsAdapter? = null

    private var _binding: SubSearchFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = SubSearchFragmentBinding.bind(view)

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        val defaultValue = resources.getBoolean(R.bool.saved_adult_show_bool_default_key)
        val adultShowValue =
            sharedPref.getBoolean(getString(R.string.saved_show_adult_bool_key), defaultValue)

        val navController = view.findNavController()
        NavigationUI.setupWithNavController(binding.toolbar, navController)

        binding.searchEditText.requestFocus()
        showSoftKeyboard()

        binding.searchEditText.doOnTextChanged { text, start, before, count ->
            viewModel.getFilmsFromRemoteSource(text.toString(), adultShowValue)
        }

        viewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)
        }

        binding.searchRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = filmsAdapter
        }
    }

    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.Success -> {
                searchFragmentLoading.visibility = View.GONE
                searchFragmentLoadingFail.visibility = View.GONE
                filmsAdapter = FilmsAdapter { film ->

                }.apply {
                    submitList(appState.filmData as MutableList<Film>)
                }

                binding.searchRecyclerView.adapter = filmsAdapter
            }

            is AppState.Loading -> {
                searchFragmentLoading.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                searchFragmentLoading.visibility = View.GONE
                searchFragmentLoadingFail.visibility = View.GONE
            }
        }
    }

    private fun showSoftKeyboard() {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.searchEditText, InputMethodManager.SHOW_IMPLICIT)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}