package com.example.assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.assignment.databinding.ActivityBoardDetailBinding
import com.example.assignment.databinding.ActivityMainBinding

class BoardDetail : AppCompatActivity() {
    private val binding by lazy { ActivityBoardDetailBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}