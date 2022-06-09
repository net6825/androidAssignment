package com.example.assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment.api.BoardApi
import com.example.assignment.databinding.ActivityMainBinding
import com.example.assignment.model.Board
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    val mAdapter = RecyclerAdapter()

    private fun loadData() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8087/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitService = retrofit.create(BoardApi::class.java)
        retrofitService.getBoards().enqueue(object : Callback<MutableList<Board>> {
            override fun onResponse(
                call: Call<MutableList<Board>>,
                response: Response<MutableList<Board>>
            ) {
                    val body = response.body()
                    body?.let {
                        mAdapter.setPostList(it)
                        it.forEach { Log.d("Response", it.toString()) }
                    }
            }

            override fun onFailure(call: Call<MutableList<Board>>, t: Throwable) {
                Log.d("Response", t.message.toString())
            }
        })
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val registerBoard = binding.registerBoard

        registerBoard.setOnClickListener {
            val intent = Intent(this, BoardWrite::class.java)
            startActivity(intent)
        }

        binding.recently.setOnClickListener {
            loadData()
        }

        binding.count.setOnClickListener {

        }

        //어뎁터 설정
        binding.recyclerView.adapter = mAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        mAdapter.setListener{ position ->
            val data = mAdapter.getItem(position)
            val intent = Intent(this, BoardDetail::class.java)
            intent.putExtra("id", data.id)
            intent.putExtra("content", data.content)
            intent.putExtra("createdAt", data.createdAt)
            intent.putExtra("count", data.count)
            intent.putExtra("title", data.title)
            intent.putExtra("password", data.password)
            startActivity(intent)
        }

        loadData()
    }

}