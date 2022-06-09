package com.example.assignment

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.model.Board
import com.example.assignment.model.PostBoard

class RecyclerAdapter() :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var postList: MutableList<Board> = mutableListOf()

    fun interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private var listener: OnItemClickListener? = null

    fun setListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    fun getItem(position: Int):Board{
        return postList[position]
    }

    fun setPostList(list: MutableList<Board>) {
        postList = list
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.board_list, parent, false)
        return ViewHolder(view,listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(postList[position])
    }

    override fun getItemCount(): Int {
        return postList.count()
    }


    class ViewHolder(view: View, listener: OnItemClickListener?) : RecyclerView.ViewHolder(view) {
        val id = view.findViewById<TextView>(R.id.boardId)
        val title = view.findViewById<TextView>(R.id.viewBoardTitle)
        val content = view.findViewById<TextView>(R.id.boardContent)
        val createdAt = view.findViewById<TextView>(R.id.boardCreatedAt)
        val count = view.findViewById<TextView>(R.id.boardCount)

        init {
            view.setOnClickListener {
                Log.d("List", "${this.layoutPosition}th item clicked")
                listener?.onItemClick(this.layoutPosition)
            }
        }

        fun bind(itemBoard: Board?) {
            id?.text = itemBoard?.id.toString()
            title?.text = itemBoard?.title
            content?.text = itemBoard?.content
            createdAt?.text = itemBoard?.createdAt
            count?.text = itemBoard?.count.toString()
        }
    }
}