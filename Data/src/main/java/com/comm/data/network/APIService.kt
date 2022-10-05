package com.comm.data.network

import com.comm.data.constants.Const
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("search/multi")
    fun search(
        @Query("query") query: String, @Query("page") pageNumber: Int,
        @Query("api_key") apiKey: String = Const.API_KEY
    ): Call<Response>
}