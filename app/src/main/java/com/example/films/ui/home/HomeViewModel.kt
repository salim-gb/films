package com.example.films.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.films.AppState
import com.example.films.model.entities.FilmDTO
import com.example.films.model.repository.Repository
import com.example.films.model.utilities.FilmsLoader

class HomeViewModel(private val repository: Repository) : ViewModel(), LifecycleObserver {

    private val _liveData = MutableLiveData<AppState>()
    fun getLiveData(): LiveData<AppState> = _liveData

    private val onLoadListener: FilmsLoader.FilmsLoaderListener =
        object : FilmsLoader.FilmsLoaderListener {
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onLoaded(filmDTO: FilmDTO) {
                _liveData.value =
                    AppState.Success(filmDTO.results)
            }

            override fun onFailed(throwable: Throwable) {
                _liveData.postValue(AppState.Error(throwable))
            }
        }

    private val loader = FilmsLoader(onLoadListener)

    @RequiresApi(Build.VERSION_CODES.N)
    fun getFilmsData() {
        _liveData.value = AppState.Loading
        loader.loadFilms()
    }
}