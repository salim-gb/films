package com.example.films.model.repository

import com.example.films.model.entities.FilmDTO
import retrofit2.Callback

interface SearchRepository {

    fun getSearchFilmFromServer(
        query: String, adult: Boolean, callback: Callback<FilmDTO>
    )
}