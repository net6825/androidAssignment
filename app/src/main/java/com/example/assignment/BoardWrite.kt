package com.example.assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.assignment.api.BoardApi
import com.example.assignment.databinding.ActivityBoardWriteBinding
import com.example.assignment.databinding.ActivityMainBinding
import com.example.assignment.model.PostBoard
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class BoardWrite : AppCompatActivity() {
    private val binding by lazy { ActivityBoardWriteBinding.inflate(layoutInflater) }
    val mAdapter = RecyclerAdapter()

    val retrofit3 = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8087/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val retrofitService3: BoardApi = retrofit3.create(BoardApi::class.java)





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Log.d("Reddd","Re")

        val cancel = binding.cancel

        cancel.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        val register = binding.saveBoard

        register.setOnClickListener {
            var title = binding?.saveTitle.text
            var password = binding?.savePassword.text
            var content = binding?.eidtContent.text
            var postBoard = PostBoard(title.toString(), content.toString(), password.toString())
            Log.d("vmvm","Re")

            retrofitService3.saveBoard(postBoard).enqueue(object: Callback<PostBoard>{
                override fun onResponse(call: Call<PostBoard>, response: Response<PostBoard>) {
                    val body = response.body()
                }

                override fun onFailure(call: Call<PostBoard>, t: Throwable) {
                    t.localizedMessage
                }

            })
        }

    }
}
