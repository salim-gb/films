package com.example.films.ui.home

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.films.AppState
import com.example.films.model.repository.Repository
import java.lang.Thread.sleep

class HomeViewModel(private val repository: Repository) : ViewModel(), LifecycleObserver {

    private val _liveData = MutableLiveData<AppState>()
    fun getLiveData(): LiveData<AppState> = _liveData

    fun getFilmsData() {
        _liveData.value = AppState.Loading
        Thread {
            sleep(1000)
            _liveData.postValue(
                AppState.Success(repository.getFilmsFromLocalStorage())
            )
        }.start()
    }
}