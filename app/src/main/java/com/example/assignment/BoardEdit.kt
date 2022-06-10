package com.example.assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.assignment.api.BoardApi
import com.example.assignment.databinding.ActivityBoardEditBinding
import com.example.assignment.databinding.ActivityMainBinding
import com.example.assignment.model.EditBoard
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BoardEdit : AppCompatActivity() {
    private val binding by lazy { ActivityBoardEditBinding.inflate(layoutInflater) }

    val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8087/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val retrofitService: BoardApi = retrofit.create(BoardApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var editableTitle = binding.editableTitle
        var editableContent = binding.editableContent
        val editBoard = binding.editBoard

        var id = intent.getStringExtra("id")!!.toLong()
        var title = intent.getStringExtra("title").toString()
        var password = intent.getStringExtra("password").toString()
        var content = intent.getStringExtra("content").toString()

        intent.getStringExtra("title")?.let { editableTitle.setText(it) }
        intent.getStringExtra("content")?.let { editableContent.setText(it) }

        editBoard.setOnClickListener {
            if (title.isBlank() && content?.isBlank() == true) {
                Toast.makeText(this.applicationContext, "빈칸이 있습니다", Toast.LENGTH_SHORT).show();
            } else {
                var editBoard = EditBoard(id, editableTitle.text.toString(), password, editableContent.text.toString())
                Log.d("ddd","${editBoard}")
                retrofitService.updateBoard(editBoard).enqueue(object : Callback<EditBoard> {
                    override fun onResponse(call: Call<EditBoard>, response: Response<EditBoard>) {
                        
                        finish()
                    }

                    override fun onFailure(call: Call<EditBoard>, t: Throwable) {
                        t.localizedMessage
                    }
                })
            }
        }

    }
}