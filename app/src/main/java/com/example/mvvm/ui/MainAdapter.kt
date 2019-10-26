package com.example.mvvm.ui

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.BuildConfig
import com.example.mvvm.R
import com.example.mvvm.data.entity.Movies
import com.example.mvvm.displayImageOriginal
import com.example.mvvm.inflate
import kotlinx.android.synthetic.main.item_movie.view.*

class MainAdapter(val context: Context? = null, private val list: ArrayList<Movies>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent.inflate(R.layout.item_movie))

    fun updateData(dataMovies: List<Movies>) {
        this.list.clear()
        this.list.addAll(dataMovies)
        notifyDataSetChanged()
    }

    @Suppress("USELESS_ELVIS")
    override fun getItemCount(): Int = list.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var movies: Movies? = null

        fun bindItem(data: Movies?) {
            this.movies = data
            itemView.run {
                tv_title.text = data?.title
                tv_popularity.text = data?.popularity.toString()
                tv_release.text = data?.releaseDate
                displayImageOriginal(context!!, iv_poster, BuildConfig.IMAGES + data?.posterPath)
            }
        }
    }
}