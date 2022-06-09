package com.example.assignment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.model.Board

class RecyclerAdapter() :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var postList:MutableList<Board> = mutableListOf()

    fun setPostList(list:MutableList<Board>){
        postList = list
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return RecyclerAdapter.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.board_list, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(postList[position])
    }

    override fun getItemCount(): Int {
        return postList.count()
    }

    class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        val id = view?.findViewById<TextView>(R.id.boardId)
        val title = view?.findViewById<TextView>(R.id.viewBoardTitle)
        val content = view?.findViewById<TextView>(R.id.boardContent)
        val createdAt = view?.findViewById<TextView>(R.id.boardCreatedAt)
        val count = view?.findViewById<TextView>(R.id.boardCount)

        fun bind(itemBoard: Board?) {
            id?.text = itemBoard?.id.toString()
            title?.text = itemBoard?.title
            content?.text = itemBoard?.content
            createdAt?.text = itemBoard?.createdAt
            count?.text = itemBoard?.count.toString()
        }
    }
}