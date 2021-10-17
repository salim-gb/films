package com.example.films.model.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Film1(
    val id: Long,
    val title: String,
    val overView: String,
    val vote_average: Double,
    val vote_count: Int,
    val popularity: Double,
    val poster_path: String,
    val release_date: String
) : Parcelable {
    companion object {
        fun filmList(): List<Film> {
            return listOf(
                Film(
                    id = 1,
                    title = "Venom: Let There Be Carnage",
                    overView = "After finding a host body in investigative reporter Eddie Brock, the alien symbiote must face a new enemy, Carnage, the alter ego of serial killer Cletus Kasady.",
                    vote_average = 7.5,
                    vote_count = 343,
                    popularity = 11173.275,
                    poster_path = "https://image.tmdb.org/t/p/w500/rjkmN1dniUHVYAtwuV3Tji7FsDO.jpg",
                    release_date = "2020-06-12"
                ),
                Film(
                    id = 2,
                    title = "Free Guy",
                    overView = "A bank teller called Guy realizes he is a background character in an open world video game called Free City that will soon go offline.",
                    vote_average = 7.9,
                    vote_count = 2393,
                    popularity = 8075.543,
                    poster_path = "https://image.tmdb.org/t/p/w500/xmbU4JTUm8rsdtn7Y3Fcm30GpeT.jpg",
                    release_date = "2021-08-11"
                ),
                Film(
                    id = 3,
                    title = "Snake Eyes: G.I. Joe Origins",
                    overView = "After saving the life of their heir apparent, tenacious loner Snake Eyes is welcomed into an ancient Japanese clan called the Arashikage where he is taught the ways of the ninja warrior. But, when secrets from his past are revealed, Snake Eyes' honor and allegiance will be tested – even if that means losing the trust of those closest to him.",
                    vote_average = 6.9,
                    vote_count = 602,
                    popularity = 4019.003,
                    poster_path = "https://image.tmdb.org/t/p/w500/aO9Nnv9GdwiPdkNO79TISlQ5bbG.jpg",
                    release_date = "2021-07-22"
                ),
                Film(
                    id = 4,
                    title = "Venom: Let There Be Carnage",
                    overView = "After finding a host body in investigative reporter Eddie Brock, the alien symbiote must face a new enemy, Carnage, the alter ego of serial killer Cletus Kasady.",
                    vote_average = 7.5,
                    vote_count = 343,
                    popularity = 11173.275,
                    poster_path = "https://image.tmdb.org/t/p/w500/rjkmN1dniUHVYAtwuV3Tji7FsDO.jpg",
                    release_date = "2020-06-12"
                ),
                Film(
                    id = 5,
                    title = "Free Guy",
                    overView = "A bank teller called Guy realizes he is a background character in an open world video game called Free City that will soon go offline.",
                    vote_average = 7.9,
                    vote_count = 2393,
                    popularity = 8075.543,
                    poster_path = "https://image.tmdb.org/t/p/w500/xmbU4JTUm8rsdtn7Y3Fcm30GpeT.jpg",
                    release_date = "2021-08-11"
                ),
                Film(
                    id = 6,
                    title = "Snake Eyes: G.I. Joe Origins",
                    overView = "After saving the life of their heir apparent, tenacious loner Snake Eyes is welcomed into an ancient Japanese clan called the Arashikage where he is taught the ways of the ninja warrior. But, when secrets from his past are revealed, Snake Eyes' honor and allegiance will be tested – even if that means losing the trust of those closest to him.",
                    vote_average = 6.9,
                    vote_count = 602,
                    popularity = 4019.003,
                    poster_path = "https://image.tmdb.org/t/p/w500/aO9Nnv9GdwiPdkNO79TISlQ5bbG.jpg",
                    release_date = "2021-07-22"
                )
            )
        }
    }
}