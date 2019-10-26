package com.example.mvvm.network

interface NetworkListener {
    fun onSuccess(msg: String)
    fun onFailure(msg: String?)
}