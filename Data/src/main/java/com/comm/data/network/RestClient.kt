package com.comm.data.network

import com.comm.data.constants.Const
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RestClient {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Const.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()

    val service: APIService by lazy {
        retrofit.create(APIService::class.java)
    }
}