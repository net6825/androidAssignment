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
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    val mAdapter = RecyclerAdapter()

    private fun loadRecently() {
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

    private fun loadCount() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8087/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitService = retrofit.create(BoardApi::class.java)
        retrofitService.getBoardsCount().enqueue(object : Callback<MutableList<Board>> {

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
            Log.d("Re","Re")
            val intent = Intent(this, BoardWrite::class.java)
            startActivity(intent)
        }

        binding.recently.setOnClickListener {
            loadRecently()
        }

        binding.count.setOnClickListener {
            loadCount()
        }

        //어뎁터 설정
        binding.recyclerView.adapter = mAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        mAdapter.setListener{ position ->
            val data = mAdapter.getItem(position)
            val intent = Intent(this, BoardDetail::class.java)

            val retrofit = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8087/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val retrofitService: BoardApi = retrofit.create(BoardApi::class.java)

            retrofitService.getBoard(data.id).enqueue(object : Callback<Board>{
                override fun onResponse(call: Call<Board>, response: Response<Board>) {
                    response.body()?.let { intent.putExtra("id", it.id) }
                    response.body()?.let { intent.putExtra("content", it.content) }
                    response.body()?.let { intent.putExtra("createdAt", it.createdAt) }
                    response.body()?.let { intent.putExtra("count", it.count) }
                    response.body()?.let { intent.putExtra("title", it.title) }
                    response.body()?.let { intent.putExtra("password", it.password) }
                    startActivity(intent)
                }

                override fun onFailure(call: Call<Board>, t: Throwable) {
                    Log.d("AA","AAA")
                }

            })

        }

        binding.searchButton.setOnClickListener {
            val searchTitle = binding.searchTitle.text.toString()
            val retrofit = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8087/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val retrofitService: BoardApi = retrofit.create(BoardApi::class.java)

            retrofitService.getBoardByTitle(searchTitle).enqueue(object:Callback<MutableList<Board>>{
                override fun onResponse(call: Call<MutableList<Board>>, response: Response<MutableList<Board>>) {
                    val body = response.body()
                    body?.let {
                        mAdapter.setPostList(it)
                    }
                    Log.d("CCCC","${body}")
                }

                override fun onFailure(call: Call<MutableList<Board>>, t: Throwable) {
                    Log.d("FA","FA")
                }

            })
        }

    }

}