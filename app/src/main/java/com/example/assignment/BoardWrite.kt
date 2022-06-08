package com.example.assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.assignment.databinding.ActivityBoardWriteBinding
import com.example.assignment.databinding.ActivityMainBinding

class BoardWrite : AppCompatActivity() {
    private val binding by lazy { ActivityBoardWriteBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val cancel = binding.cancel

        cancel.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}