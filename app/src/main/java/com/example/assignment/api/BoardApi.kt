package com.example.assignment.api

import com.example.assignment.model.Board
import com.example.assignment.model.EditBoard
import com.example.assignment.model.PostBoard
import com.example.assignment.model.RemoveBoard
import retrofit2.Call
import retrofit2.http.*

interface BoardApi {
    @GET("dashboard")
    fun getBoards(): Call<MutableList<Board>>

    @GET("dashboard/count")
    fun getBoardsCount(): Call<MutableList<Board>>

    @GET("dashboard/{id}")
    fun getBoard(@Path("id") id: Long): Call<Board>

    @GET("dashboard/title")
    fun getBoardByTitle(@Query("title") title:String): Call<MutableList<Board>>

    @Headers("Content-Type: application/json")
    @POST("dashboard")
    fun saveBoard(@Body postBoard: PostBoard): Call<PostBoard>

    @Headers("Content-Type: application/json")
    @PATCH("dashboard")
    fun updateBoard(@Body editBoard: EditBoard): Call<EditBoard>

    @Headers("Content-Type: application/json")
    @DELETE("dashboard")
    fun removeBoard(@Query("id") id: Long): Call<String>
}