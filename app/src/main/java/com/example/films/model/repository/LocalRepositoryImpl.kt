package com.example.films.model.repository

import com.example.films.model.entities.Film
import com.example.films.room.HistoryDao

class LocalRepositoryImpl(private val localDataSource: HistoryDao): LocalRepository {
    override fun getAllHistory(): List<Film> {
        return listOf()
    }

    override fun saveEntity(film: Film) {
        TODO("Not yet implemented")
    }
}