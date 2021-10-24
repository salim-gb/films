package com.example.films.model.repository

import com.example.films.model.entities.Film
import retrofit2.Callback

class DetailsRepositoryImpl(private val remoteDataSource: RemoteDataSource) :
    DetailsRepository {

    override fun getFilmDetailFromServer(id: Long, callback: Callback<Film>) {
        remoteDataSource.getFilmDetails(id, callback)
    }
}