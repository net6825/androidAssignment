package com.example.assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.example.assignment.api.BoardApi
import com.example.assignment.databinding.ActivityBoardDetailBinding
import com.example.assignment.databinding.ActivityMainBinding
import com.example.assignment.model.RemoveBoard
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BoardDetail : AppCompatActivity() {
    private val binding by lazy { ActivityBoardDetailBinding.inflate(layoutInflater) }

    val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8087/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val retrofitService: BoardApi = retrofit.create(BoardApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val boardNum = binding.boardNum
        val boardTitle = binding.boardTitle
        val createdAt = binding.createdAt
        val counting = binding.counting
        val detailContent = binding.detailContent

//        viewModel.board.observe(this) { Log.d("Response", "${it.toString()}") }
        val id = intent.getLongExtra("id", 0)
        id.let { boardNum.text = it.toString() }
        intent.getStringExtra("content")?.let { detailContent.text = it }
        intent.getStringExtra("createdAt")?.let { createdAt.text = it }
        intent.getStringExtra("title")?.let { boardTitle.text = it }
        intent.getIntExtra("count", 0).let { counting.text = it.toString() }
        val realPassword = intent.getStringExtra("password")

        binding.edit.setOnClickListener {
            val password = binding.editPassword.text.toString()
            if (realPassword.equals(password)) {
                var intent = Intent(this, BoardEdit::class.java)

                intent.putExtra("id", boardNum.text)
                intent.putExtra("title", boardTitle.text)
                intent.putExtra("password", realPassword)
                intent.putExtra("content", detailContent.text)

                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this.applicationContext, "비밀번호가 틀렸습니다", Toast.LENGTH_SHORT).show();
            }
        }

        binding.delete.setOnClickListener {
            val password = binding.editPassword.text.toString()
            if (realPassword.equals((password))) {
                retrofitService.removeBoard(id).enqueue(object : Callback<String> {
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        Log.d("QQQQQQQQ", "${response.body()}")
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                    }

                })
                finish()

            } else {
                Toast.makeText(this.applicationContext, "비밀번호가 틀렸습니다", Toast.LENGTH_SHORT).show();
            }
        }
    }
}