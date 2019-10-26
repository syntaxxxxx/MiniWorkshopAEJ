package com.example.mvvm.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.mvvm.data.entity.Movies
import com.example.mvvm.data.entity.ResponseMovies
import com.example.mvvm.network.Network
import com.example.mvvm.network.NetworkListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesRepository {

    val _result = MutableLiveData<List<Movies>>()

    fun getMovies(networkListener: NetworkListener?) {
        Network.routes().getMovies().enqueue(object : Callback<ResponseMovies> {

            override fun onResponse(call: Call<ResponseMovies>, response: Response<ResponseMovies>) {
                if (response.isSuccessful) {
                    networkListener?.onSuccess()
                    _result.value = response.body()?.results

                } else{
                    networkListener?.onFailure(response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<ResponseMovies>, t: Throwable) {
                networkListener?.onFailure(t.message)
            }
        })
    }

}