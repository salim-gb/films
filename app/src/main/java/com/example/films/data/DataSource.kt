package com.example.films.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DataSource {
    private val initialFilmList = filmList()
    private val filmsLiveData = MutableLiveData(initialFilmList)

    fun getFilmForId(id: Long): Film? {
        filmsLiveData.value?.let { films ->
            return films.firstOrNull{it.id == id}
        }
        return null
    }

    fun getFilmsList(): LiveData<List<Film>> {
        return filmsLiveData
    }

    companion object {
        private var INSTANCE: DataSource? = null

        fun getDataSource(): DataSource {
            return synchronized(DataSource::class) {
                val newInstance = INSTANCE ?: DataSource()
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}