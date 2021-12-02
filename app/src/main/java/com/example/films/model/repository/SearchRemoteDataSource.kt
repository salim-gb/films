package com.example.films.model.repository

import com.example.films.BuildConfig
import com.example.films.SearchApi
import com.example.films.model.entities.FilmDTO
import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchRemoteDataSource {

    private val searchApi = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
        .build().create(SearchApi::class.java)

    fun getSearchFilms(query: String, adult: Boolean, callback: Callback<FilmDTO>) {
        searchApi.getFilms(BuildConfig.API_KEY, "ru-RR", query, 1, adult).enqueue(callback)
    }
}