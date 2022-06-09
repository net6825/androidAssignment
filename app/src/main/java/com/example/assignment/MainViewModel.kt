package com.example.assignment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment.api.JsServer
import com.example.assignment.model.Board
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {
    val error = MutableLiveData<String>()

    val boardList = MutableLiveData<List<Board>>()
    var board = MutableLiveData<Board>()

    lateinit var requestList: Call<MutableList<Board>>
    lateinit var request: Call<Board>

    fun getBoard(id:Long) = viewModelScope.launch {
        request = JsServer.boardAPi.getBoard(id)
        request.enqueue(object:Callback<Board>{
            override fun onResponse(call: Call<Board>, response: Response<Board>) {
                board.value = response.body()
            }

            override fun onFailure(call: Call<Board>, t: Throwable) {
                error.value = t.localizedMessage
            }

        })
    }

    fun getBoard() = viewModelScope.launch {
        requestList.enqueue(object:Callback<MutableList<Board>>{
            override fun onResponse(call: Call<MutableList<Board>>, response: Response<MutableList<Board>>) {
                boardList.value = response.body()
                Log.d("RESPONSE", "Response: ${response.code()}")
            }

            override fun onFailure(call: Call<MutableList<Board>>, t: Throwable) {
                error.value = t.localizedMessage
            }
        })
    }

    override fun onCleared() {
        super.onCleared()
        if(::requestList.isInitialized) requestList.cancel()
        if(::request.isInitialized) request.cancel()
    }
}