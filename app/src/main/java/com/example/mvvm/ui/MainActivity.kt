package com.example.mvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm.R
import com.example.mvvm.data.entity.Movies
import com.example.mvvm.di.Injection
import com.example.mvvm.network.NetworkListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.shimmer_layout.*

class MainActivity : AppCompatActivity(), NetworkListener {

    private val data = arrayListOf<Movies>()

    private val adapter: MainAdapter by lazy {
        MainAdapter(this@MainActivity, data)
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel =
            ViewModelProviders
                .of(this, Injection.provideViewModelFactory(this))
                .get(MainViewModel::class.java)

        swipe_to_refresh_layout.setOnRefreshListener {
            fetchData()
        }
        initObserver()
        fetchData()
    }

    private fun initObserver() {
        viewModel.networkListener = this
        viewModel.getMovies().observe(this, Observer { listMovies ->
            showDataMovie(listMovies)
        })
    }

    private fun showDataMovie(listMovies: List<Movies>) {
        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(this@MainActivity)
        adapter.updateData(listMovies)
        rv.adapter = adapter
    }

    private fun fetchData() {
        startShimmer()
        viewModel.fetchData()
    }

    override fun onSuccess(data: String) {
        Log.d("TAG", data)
        stopShimmer()
        swipe_to_refresh_layout.isRefreshing = false
    }

    override fun onFailure(msg: String?) {
        Log.e("TAG", msg!!)
        stopShimmer()
        swipe_to_refresh_layout.isRefreshing = false
    }

    private fun startShimmer() {
        rv.visibility = View.GONE
        shimmering_layout.visibility = View.VISIBLE
        shimmering_layout.startShimmer()
    }

    private fun stopShimmer() {
        rv.visibility = View.VISIBLE
        shimmering_layout.stopShimmer()
        shimmering_layout.visibility = View.GONE
    }

    override fun onPause() {
        super.onPause()
        stopShimmer()
    }
}
