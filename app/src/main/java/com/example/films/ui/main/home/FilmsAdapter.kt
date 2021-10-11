package com.example.films.ui.main.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.films.R
import com.example.films.data.Film
import timber.log.Timber

class FilmsAdapter(private val onClick: (Film) -> Unit) :
    ListAdapter<Film, FilmsAdapter.FilmsViewHolder>(FilmDiffCallback) {

    inner class FilmsViewHolder(itemView: View, val onClick: (Film) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val filmImageView: ImageView = itemView.findViewById(R.id.film_poster)
        private val filmTitle: TextView = itemView.findViewById(R.id.film_title)
        private val filmReleaseDate: TextView = itemView.findViewById(R.id.film_release_date)
        private var currentFilm: Film? = null

        init {
            itemView.setOnClickListener {
                currentFilm?.let {
                    onClick(it)
                }
            }
        }

        fun bind(film: Film) {
            currentFilm = film

            filmTitle.text = film.title
            filmReleaseDate.text = film.release_date
            Glide.with(itemView)
                .load(film.poster_path)
                .centerCrop()
                .into(filmImageView)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.film_item, parent, false)
        return FilmsViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {
        val film = getItem(position)
        holder.bind(film)
    }
}

object FilmDiffCallback : DiffUtil.ItemCallback<Film>() {
    override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem.id == newItem.id
    }

}