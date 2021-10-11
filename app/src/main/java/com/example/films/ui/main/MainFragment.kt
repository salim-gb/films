package com.example.films.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.films.R
import com.example.films.databinding.MainFragmentBinding
import com.example.films.ui.main.favorite.FavoriteFragment
import com.example.films.ui.main.home.HomeFragment

class MainFragment : Fragment(R.layout.main_fragment) {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: MainFragmentBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = MainFragmentBinding.bind(view)
        _binding = binding

        if (savedInstanceState == null) {
            childFragmentManager.beginTransaction()
                .replace(R.id.container_main, HomeFragment.newInstance())
                .commit()
        }

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    childFragmentManager.beginTransaction()
                        .replace(R.id.container_main, HomeFragment.newInstance())
                        .commit()
                    return@setOnItemSelectedListener true
                }
                R.id.favorite -> {
                    childFragmentManager.beginTransaction()
                        .replace(R.id.container_main, FavoriteFragment.newInstance())
                        .commit()
                    return@setOnItemSelectedListener true
                }
            }
            return@setOnItemSelectedListener false
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}