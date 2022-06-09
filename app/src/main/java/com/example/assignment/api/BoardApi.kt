package com.example.assignment.api

import com.example.assignment.model.Board
import com.example.assignment.model.PostBoard
import retrofit2.Call
import retrofit2.http.*

interface BoardApi {
    @GET("dashboard")
    fun getBoards(): Call<MutableList<Board>>

    @GET("dashboard/count")
    fun getBoardsCount(): Call<MutableList<Board>>

    @GET("dashboard/{id}")
    fun getBoard(@Path("id") id:Long): Call<Board>

    @Headers("Content-Type: application/json")
    @POST("dashboard")
    fun saveBoard(@Body postBoard: PostBoard): Call<PostBoard>
}