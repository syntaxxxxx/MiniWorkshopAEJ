package com.example.mvvm.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.mvvm.data.entity.Movies
import com.example.mvvm.data.entity.ResponseMovies
import com.example.mvvm.network.Network
import com.example.mvvm.network.NetworkListener
import com.example.mvvm.network.Routes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository(private val routes: Routes) {

    val _result = MutableLiveData<List<Movies>>()

    fun getMovies(networkListener: NetworkListener?) {
        routes.getMovies().enqueue(object : Callback<ResponseMovies> {

            override fun onResponse(call: Call<ResponseMovies>, response: Response<ResponseMovies>) {
                if (response.isSuccessful) {
                    networkListener?.onSuccess(response.body()?.results.toString())
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