package com.example.films.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.films.data.DataSource
import com.example.films.data.Film
import java.lang.IllegalArgumentException

class FilmDetailViewModel(private val dataSource: DataSource): ViewModel() {

    fun getFilmForId(id: Long) : Film? {
        return dataSource.getFilmForId(id)
    }
}

class FilmDetailViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FilmDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FilmDetailViewModel(DataSource.getDataSource()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}