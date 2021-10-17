package com.example.films.model.repository

import com.example.films.model.entities.Film
import com.example.films.model.entities.Film1

class RepositoryImpl : Repository {

    override fun getFilmsFromServer(): List<Film> {
        return emptyList()
    }

    override fun getFilmsFromLocalStorage() = Film1.filmList()
}