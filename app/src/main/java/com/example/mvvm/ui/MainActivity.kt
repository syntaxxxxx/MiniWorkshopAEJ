package com.example.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvvm.network.NetworkListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NetworkListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipe_to_refresh_layout.setOnRefreshListener {
            fetchData()
        }
    }

    private fun fetchData() {

    }

    override fun onSuccess() {

    }

    override fun onFailure(msg: String?) {
    }
}
