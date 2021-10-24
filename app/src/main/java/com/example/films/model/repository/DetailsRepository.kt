package com.example.films.model.repository

import com.example.films.model.entities.Film
import retrofit2.Callback

interface DetailsRepository {
    fun getFilmDetailFromServer(
        id: Long, callback: Callback<Film>
    )
}