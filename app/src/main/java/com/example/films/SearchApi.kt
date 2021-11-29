package com.example.films

import com.example.films.model.entities.Film
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("3/search/movie?")
    fun getFilms(
        @Query("api_key") key: String,
        @Query("language") language: String,
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("include_adult") adult: Boolean
    ): Call<Film>
}