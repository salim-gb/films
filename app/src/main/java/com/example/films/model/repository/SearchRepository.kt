package com.example.films.model.repository

import com.example.films.model.entities.Film
import retrofit2.Callback

interface SearchRepository {

    fun getSearchFilmFromServer(
        query: String, callback: Callback<Film>
    )
}