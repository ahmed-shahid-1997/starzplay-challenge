package com.comm.moviesearch

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.VideoView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.comm.data.constants.Const
import com.comm.data.model.MediaItem
import com.comm.data.model.MediaTypes

@BindingAdapter("showBackdrop")
fun showBackdrop(view: ImageView, mediaItem: MediaItem) {
    var url = "${Const.IMAGE_BASE_URL}/${Const.POSTER_SIZE}/"
    url += if (mediaItem.backdropPath != null) mediaItem.backdropPath
    else mediaItem.posterPath
    Glide.with(view)
        .load(url)
        .placeholder(getPlaceholderId(mediaItem))
        .into(view)
}

@BindingAdapter("showPoster")
fun showPoster(view: ImageView, mediaItem: MediaItem) {
    var url = "${Const.IMAGE_BASE_URL}/${Const.POSTER_SIZE}/"
    url += if (mediaItem.posterPath != null) mediaItem.posterPath
    else mediaItem.backdropPath
    Glide.with(view)
        .load(url)
        .placeholder(getPlaceholderId(mediaItem))
        .into(view)
}

private fun getPlaceholderId(mediaItem: MediaItem) =
    if (mediaItem.mediaType == MediaTypes.MOVIE.value) R.drawable.ic_movie else if (mediaItem.mediaType == MediaTypes.TV.value) R.drawable.ic_tv else R.drawable.ic_person

@BindingAdapter("showDate")
fun showDate(view: TextView, mediaItem: MediaItem) {
    view.text = if (mediaItem.releaseDate != null) mediaItem.releaseDate else mediaItem.firstAirDate
}

@BindingAdapter("showTitle")
fun showTitle(view: TextView, mediaItem: MediaItem) {
    view.text = mediaItem.displayName
}

@BindingAdapter("showRating")
fun showRating(view: TextView, mediaItem: MediaItem) {
    view.text =
        if (mediaItem.voteAverage != null) mediaItem.voteAverage.toString() else mediaItem.popularity.toString()
}

@BindingAdapter("showHidePeopleIcon")
fun showHidePeopleIcon(view: ImageView, mediaItem: MediaItem) {
    view.visibility = if (mediaItem.voteCount == null) View.GONE else View.VISIBLE
}

@BindingAdapter("showOriginalTitle")
fun showOriginalTitle(view: TextView, mediaItem: MediaItem) {
    view.text = view.context.getString(R.string.original_title_placeholder, mediaItem.originalTitle)
}

@BindingAdapter("playVideo")
fun playVideo(view: VideoView, url: String) {
    view.setVideoURI(Uri.parse(url))
    view.start()
}

