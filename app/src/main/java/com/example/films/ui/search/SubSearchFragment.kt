package com.example.films.ui.search

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.films.R
import com.example.films.databinding.SubSearchFragmentBinding


class SubSearchFragment : Fragment(R.layout.sub_search_fragment) {
    private var _binding: SubSearchFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = SubSearchFragmentBinding.bind(view)

        val navController = view.findNavController()
        NavigationUI.setupWithNavController(binding.toolbar, navController)

        binding.searchEditText.requestFocus()
        showSoftKeyboard()

        binding.searchEditText.doOnTextChanged { text, start, before, count ->
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()


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