package id.nns.naruto_movie_app_v1.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NarutoMovieData(
    val id: Int?,
    val img: Int?,
    val title: String?,
    val date: String?,
    val duration: String?,
    val score: Int?,
    val overview: String?
) : Parcelable