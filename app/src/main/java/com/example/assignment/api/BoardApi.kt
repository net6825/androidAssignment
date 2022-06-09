package com.example.assignment.api

import com.example.assignment.model.Board
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BoardApi {
    @GET("dashboard")
    fun getBoards(): Call<MutableList<Board>>

    @GET("dashboard/{id}")
    fun getBoard(@Path("id") id:Long): Call<Board>
}