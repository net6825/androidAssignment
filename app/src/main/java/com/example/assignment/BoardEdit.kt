package com.example.assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.assignment.databinding.ActivityBoardEditBinding
import com.example.assignment.databinding.ActivityMainBinding

class BoardEdit : AppCompatActivity() {
    private val binding by lazy { ActivityBoardEditBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}