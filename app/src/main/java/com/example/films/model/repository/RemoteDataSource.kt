package com.example.films.model.repository

import com.example.films.BuildConfig
import com.example.films.FilmAPI
import com.example.films.model.entities.Film
import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {
    private val filmApi = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
        .build().create(FilmAPI::class.java)

    fun getFilmDetails(id: Long, callback: Callback<Film>) {
        filmApi.getFilm(id, BuildConfig.API_KEY).enqueue(callback)
    }
}