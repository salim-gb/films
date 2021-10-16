package com.example.films.model.repository

import com.example.films.model.entities.Film

class RepositoryImpl: Repository {
    override fun getFilmsFromServer() = Film.filmList()
    override fun getFilmsFromLocalStorage() = Film.filmList()
}