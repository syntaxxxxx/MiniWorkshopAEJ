package com.example.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm.data.entity.Movies
import com.example.mvvm.data.repository.MoviesRepositoryImpl
import com.example.mvvm.network.NetworkListener

class MainViewModel(private val repository: MoviesRepositoryImpl) : ViewModel() {
    var networkListener: NetworkListener? = null
    fun getMovies(): LiveData<List<Movies>> = repository._result
    fun fetchData() = repository.getMovies(networkListener)
}