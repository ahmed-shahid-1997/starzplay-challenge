package com.comm.moviesearch.model

import com.comm.data.model.MediaItem

data class Carousel(
    val title: String,
    val movies: List<MediaItem>
)
