package com.comm.data.network

import com.comm.data.model.MediaItem
import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("total_results")
    val totalResults: Int?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("results")
    val results: List<MediaItem>?,
)