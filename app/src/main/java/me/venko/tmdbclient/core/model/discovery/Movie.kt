package me.venko.tmdbclient.core.model.discovery


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @author Victor Kosenko
 *
 */
@JsonClass(generateAdapter = true)
data class Movie(
    @Json(name = "vote_count")
    val voteCount: Int,
    val id: Int,
    val video: Boolean,
    @Json(name = "vote_average")
    val voteAverage: Double,
    val title: String,
    val popularity: Double,
    @Json(name = "poster_path")
    val posterPath: String?,
    @Json(name = "original_language")
    val originalLanguage: String,
    @Json(name = "original_title")
    val originalTitle: String,
    @Json(name = "genre_ids")
    val genreIds: List<Int>,
    @Json(name = "backdrop_path")
    val backdropPath: String?,
    val adult: Boolean,
    val overview: String,
    @Json(name = "release_date")
    val releaseDate: String
)
