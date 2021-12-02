package com.example.films.model.repository

import com.example.films.model.entities.FilmDTO
import retrofit2.Callback

class SearchRepositoryImpl(private val remoteDataSource: SearchRemoteDataSource) :
    SearchRepository {
    override fun getSearchFilmFromServer(query: String, adult: Boolean, callback: Callback<FilmDTO>) {
        remoteDataSource.getSearchFilms(query, adult, callback)
    }
}