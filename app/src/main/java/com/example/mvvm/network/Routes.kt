package com.example.mvvm.network

import com.example.mvvm.data.entity.ResponseMovies
import retrofit2.http.GET

interface Routes {

    @GET("discover/movie")
    fun getMovies() : retrofit2.Call<ResponseMovies>

}