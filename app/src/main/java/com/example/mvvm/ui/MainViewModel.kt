package com.example.mvvm.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm.data.entity.Movies
import com.example.mvvm.data.repository.MovieRepository
import com.example.mvvm.network.NetworkListener

class MainViewModel(private val repository: MovieRepository) : ViewModel() {
    var networkListener: NetworkListener? = null
    fun getMovies(): LiveData<List<Movies>> = repository._result
    fun fetchData() = repository.getMovies(networkListener)
}