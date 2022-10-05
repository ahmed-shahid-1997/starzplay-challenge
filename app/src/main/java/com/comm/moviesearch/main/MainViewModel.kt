package com.comm.moviesearch.main

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.comm.data.constants.Const
import com.comm.data.model.MediaItem
import com.comm.data.network.Response
import com.comm.data.network.RestClient
import com.comm.data.model.ResultOf
import com.comm.moviesearch.model.Carousel
import retrofit2.Call
import retrofit2.Callback

class MainViewModel : ViewModel() {
    val primaryData = MediatorLiveData<ResultOf<List<Carousel>>>()
    val secondaryData = MutableLiveData<List<Carousel>>()
    private val response = MutableLiveData<ResultOf<Response>>()
    private lateinit var query: String
    private var page = 1

    init {
        primaryData.addSource(response) {
            when (it) {
                is ResultOf.Success -> {
                    it.item.results?.run {
                        if ((it.item.page ?: 1) > 1) secondaryData.postValue(carousels())
                        else primaryData.postValue(ResultOf.Success(carousels()))
                    }
                    if (page < (it.item.totalPages ?: 1)) {
                        ++page
                        callAPI()
                    }
                }
                is ResultOf.Failure -> primaryData.postValue(
                    ResultOf.Failure(
                        it.message,
                        it.throwable
                    )
                )
            }
        }
    }

    private fun List<MediaItem>.carousels(): List<Carousel> {
        val types = map { movie -> movie.mediaType }.distinct()
        val items = types.map { type ->
            Carousel(
                type.capitalize(),
                filter { mediaItem -> mediaItem.mediaType == type })
        }.sortedBy { c -> c.title }
        return items
    }

    fun search(query: String) {
        page = 1
        this.query = query
        callAPI()
    }

    private fun callAPI() {
        val call = RestClient.service.search(query, page)
        call.enqueue(buildCallback(response))
    }

    private fun <T> buildCallback(
        liveData: MutableLiveData<ResultOf<T>>
    ): Callback<T> {
        return object : Callback<T> {
            override fun onResponse(call: Call<T>, response: retrofit2.Response<T>) {
                if (response.isSuccessful && response.body() != null)
                    liveData.postValue(ResultOf.Success(response.body()!!))
                else postError(null, null)
            }

            override fun onFailure(call: Call<T>, t: Throwable) =
                postError(t.message, t)

            private fun postError(message: String?, throwable: Throwable?) =
                liveData.postValue(ResultOf.Failure(message ?: Const.DEFAULT_ERROR, throwable))

        }
    }
}