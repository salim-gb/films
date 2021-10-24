package com.example.films

import com.example.films.model.entities.Film
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmAPI {
    @GET("3/movie/{id}?")
    fun getFilm(
        @Path("id") id: Long,
        @Query("api_key") key: String
    ): Call<Film>
}