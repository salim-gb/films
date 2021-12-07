package com.example.films.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.films.BuildConfig
import com.example.films.R
import com.example.films.databinding.SettingsFragmentBinding

class SettingsFragment : Fragment(R.layout.settings_fragment) {

    private var _binding: SettingsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = SettingsFragmentBinding.bind(view)

        val navController = view.findNavController()
        NavigationUI.setupWithNavController(binding.settingToolbar, navController)

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        val defaultValue = resources.getBoolean(R.bool.saved_adult_show_bool_default_key)
        val adultShowValue =
            sharedPref.getBoolean(getString(R.string.saved_show_adult_bool_key), defaultValue)

        binding.switchShowAdult.isChecked = adultShowValue

        binding.switchShowAdult.setOnCheckedChangeListener { buttonView, isChecked ->
            val sharedPreferences =
                activity?.getPreferences(Context.MODE_PRIVATE) ?: return@setOnCheckedChangeListener
            with(sharedPreferences.edit()) {
                putBoolean(getString(R.string.saved_show_adult_bool_key), isChecked)
                apply()
            }
        }
    }
}