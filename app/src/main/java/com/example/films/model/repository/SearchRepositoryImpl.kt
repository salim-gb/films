package com.example.films.model.repository

import com.example.films.model.entities.Film
import retrofit2.Callback

class SearchRepositoryImpl(private val remoteDataSource: SearchRemoteDataSource) :
    SearchRepository {
    override fun getSearchFilmFromServer(query: String, callback: Callback<Film>) {
        remoteDataSource.getSearchFilms(query, callback)
    }
}