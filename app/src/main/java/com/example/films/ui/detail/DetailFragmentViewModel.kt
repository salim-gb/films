package com.example.films.ui.detail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.films.model.entities.Film
import com.example.films.model.utilities.FilmsDetailLoader


class FilmDetailViewModel : ViewModel() {
    private val _liveData = MutableLiveData<Film>()
    val liveData: LiveData<Film> = _liveData

    private val onLoadListener: FilmsDetailLoader.FilmsDetailLoaderListener =
        object : FilmsDetailLoader.FilmsDetailLoaderListener {
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onLoaded(film: Film) {
                _liveData.value = film
            }

            override fun onFailed(throwable: Throwable) {
                TODO("Not yet implemented")
            }
        }

    private val loader = FilmsDetailLoader(onLoadListener)

    @RequiresApi(Build.VERSION_CODES.N)
    fun getFilmForId(id: Long) {
        loader.loadFilms(id)
    }
}
