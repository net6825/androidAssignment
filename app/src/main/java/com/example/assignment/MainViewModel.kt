package com.example.assignment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment.model.Board
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {
    val error = MutableLiveData<String>()
    val board = MutableLiveData<List<Board>>()
    lateinit var request: Call<MutableList<Board>>

    fun getBoard() = viewModelScope.launch {
        request.enqueue(object:Callback<MutableList<Board>>{
            override fun onResponse(call: Call<MutableList<Board>>, response: Response<MutableList<Board>>) {
                board.value = response.body()
                Log.d("RESPONSE", "Response: ${response.code()}")
            }

            override fun onFailure(call: Call<MutableList<Board>>, t: Throwable) {
                error.value = t.localizedMessage
            }
        })
    }

    override fun onCleared() {
        super.onCleared()
        if(::request.isInitialized) request.cancel()
    }
}