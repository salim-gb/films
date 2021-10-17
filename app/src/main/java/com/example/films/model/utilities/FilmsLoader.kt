package com.example.films.model.utilities

import android.os.Build
import android.os.Handler
import android.os.Looper
import androidx.annotation.RequiresApi
import com.example.films.BuildConfig
import com.example.films.model.entities.FilmDTO
import com.google.gson.Gson
import timber.log.Timber
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

class FilmsLoader(private val listener: FilmsLoaderListener) {
    @RequiresApi(Build.VERSION_CODES.N)
    fun loadFilms() {
        try {
            val uri =
                URL("https://api.themoviedb.org/3/movie/popular?api_key=${BuildConfig.API_KEY}")
            val handler = Handler(Looper.getMainLooper())
            Thread {
                lateinit var urlConnection: HttpsURLConnection
                try {
                    urlConnection = uri.openConnection() as HttpsURLConnection
                    urlConnection.requestMethod = "GET"
                    urlConnection.readTimeout = 10000
                    val bufferedReader =
                        BufferedReader(InputStreamReader(urlConnection.inputStream))

                    val filmDTO: FilmDTO =
                        Gson().fromJson(getLines(bufferedReader), FilmDTO::class.java)
                    handler.post {
                        listener.onLoaded(filmDTO)
                    }
                } catch (e: Exception) {
                    Timber.e(e, "Fail connection")
                    e.printStackTrace()
                    listener.onFailed(e)
                } finally {
                    urlConnection.disconnect()
                }
            }.start()
        } catch (e: MalformedURLException) {
            Timber.e(e, "Fail URI")
            e.printStackTrace()
            listener.onFailed(e)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }

    interface FilmsLoaderListener {
        fun onLoaded(filmDTO: FilmDTO)
        fun onFailed(throwable: Throwable)
    }
}