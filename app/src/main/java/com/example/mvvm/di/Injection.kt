package com.example.mvvm.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.data.repository.MovieRepository
import com.example.mvvm.network.Network
import com.example.mvvm.network.Routes
import com.example.mvvm.ui.ViewModelFactory

object Injection {
    fun provideRepository(): MovieRepository {
        return MovieRepository(Network.routes())
    }

    fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
        return ViewModelFactory(provideRepository())
    }
}