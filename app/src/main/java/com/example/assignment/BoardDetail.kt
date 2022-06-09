package com.example.assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.assignment.databinding.ActivityBoardDetailBinding
import com.example.assignment.databinding.ActivityMainBinding

class BoardDetail : AppCompatActivity() {
    private val binding by lazy { ActivityBoardDetailBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val boardNum = binding.boardNum
        val boardTitle = binding.boardTitle
        val createdAt = binding.createdAt
        val counting = binding.counting
        val detailContent = binding.detailContent

        viewModel.board.observe(this){
            Log.d("Response", "${it.toString()}")
        }
        intent.getLongExtra("id", 0).let {
            boardNum.text=it.toString()
        }
        intent.getStringExtra("content")?.let{
            detailContent.text=it
        }
        intent.getStringExtra("createdAt")?.let{
            createdAt.text = it
        }
        intent.getStringExtra("title")?.let {
            boardTitle.text= it
        }
        intent.getIntExtra("count", 0).let {
            counting.text= it.toString()
        }
    }
}