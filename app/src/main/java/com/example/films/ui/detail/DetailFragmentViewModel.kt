package com.example.films.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.films.model.entities.Film

class FilmDetailViewModel : ViewModel() {
    private val _liveData = MutableLiveData(Film.filmList())
    private val liveData: LiveData<List<Film>> = _liveData

    fun getFilmForId(id: Long): Film? {
        liveData.value?.let { films ->
            return films.firstOrNull { it.id == id }
        }
        return null
    }
}
