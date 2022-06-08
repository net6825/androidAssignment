package com.example.assignment.api

import com.example.assignment.model.Board
import retrofit2.Call
import retrofit2.http.GET

interface BoardApi {
    @GET("dashboard")
    fun getBoard(): Call<List<Board>>
}