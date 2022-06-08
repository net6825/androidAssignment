package com.example.assignment.model

import java.time.LocalDateTime

data class Board(var id:Long, var title:String, var password:String, var content:String, var createdAt:LocalDateTime, var count:Int)
