package com.example.assignment.model

import com.google.gson.annotations.SerializedName

data class Board(
    var id:Long,
    var title: String,
    var password:String,
    var content:String,
    var createdAt:String,
    var count:Int
    )

data class PostBoard(
    //serializedName = 키를 원하는 이름대로 정하는 것
    @SerializedName("title") var title: String?,
    @SerializedName("content") var content: String?,
    @SerializedName("password") var password: String?
)
