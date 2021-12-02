package com.example.films.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.films.AppState
import com.example.films.model.entities.Film
import com.example.films.model.entities.FilmDTO
import com.example.films.model.repository.SearchRemoteDataSource
import com.example.films.model.repository.SearchRepository
import com.example.films.model.repository.SearchRepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val SERVER_ERROR = "Ошибка сервера"
private const val REQUEST_ERROR = "Ошибка запроса на сервер"

class SearchViewModel(
    private val searchLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private val searchRepositoryImpl: SearchRepository = SearchRepositoryImpl(
        SearchRemoteDataSource()
    )
) : ViewModel() {
    fun getLiveData() = searchLiveData

    fun getFilmsFromRemoteSource(query: String, adult: Boolean) {
        searchLiveData.value = AppState.Loading
        searchRepositoryImpl.getSearchFilmFromServer(query, adult, callback)
    }

    private val callback = object : Callback<FilmDTO> {
        override fun onResponse(call: Call<FilmDTO>, response: Response<FilmDTO>) {
            val serverResponse: FilmDTO? = response.body()
            searchLiveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    AppState.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<FilmDTO>, t: Throwable) {
            searchLiveData.postValue(AppState.Error(Throwable(t.message ?: REQUEST_ERROR)))
        }
    }

    private fun checkResponse(serverResponse: FilmDTO): AppState {
        return AppState.Success(serverResponse.results)
    }
}