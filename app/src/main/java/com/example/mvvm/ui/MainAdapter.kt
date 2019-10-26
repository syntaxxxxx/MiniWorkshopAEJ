package com.example.mvvm

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.data.entity.Movies

class MainAdapter(private val context: Context) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder =
        ViewHolder(parent.inflate(R.layout.item_movie))

    @Suppress("USELESS_ELVIS")
    override fun getItemCount(): Int = 0

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(data: Movies?) {

        }
    }
}