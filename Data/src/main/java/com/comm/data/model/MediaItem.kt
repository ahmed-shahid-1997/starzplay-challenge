package com.comm.data.model

import com.comm.data.constants.Const
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MediaItem(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    @SerializedName("vote_count")
    val voteCount: Int? = null,
    @SerializedName("video")
    val isVideo: Boolean? = null,
    @SerializedName("media_type")
    val mediaType: String,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("original_name")
    val originalTitle: String? = null,
    @SerializedName("popularity")
    val popularity: Double? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("first_air_date")
    val firstAirDate: String? = null,
    @SerializedName("name")
    val name: String? = null,
) : Serializable {
    val displayName get() = title ?: name
    val showOriginalTitle get() = originalTitle != null
    val showPopularity get() = popularity != null
    val showVoteCount get() = voteCount != null
    val showVoteAverage get() = voteAverage != null
    val isPlayable get() = mediaType == MediaTypes.MOVIE.value || mediaType == MediaTypes.TV.value
    val url get() = if (isPlayable) Const.URL else throw IllegalStateException("Item is not playable")
}
