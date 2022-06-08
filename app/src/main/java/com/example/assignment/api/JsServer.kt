package com.example.assignment.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JsServer {
    companion object{
        private const val url = "http://192.168.1.29:8087"
        private var server: Retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var boardApi:BoardApi = server.create(BoardApi::class.java)
    }
}