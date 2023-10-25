package com.lifecycleaware

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.lifecycleaware.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var networkObserver: NetworkObserver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // --> Initialize the network observer in your activity or fragment
        networkObserver = NetworkObserver(this, lifecycle)
        lifecycle.addObserver(networkObserver)

        // --> Use live data to observe the network changes
        networkObserver.networkAvailableStateFlow.asLiveData().observe(this, Observer { networkState ->
            when (networkState) {
                NetworkState.Unavailable -> {
                    binding.tv.text = "Unavailable"
                    SnackBarDisplay.showNetworkUnavailableAlert(binding.root)
                }
                NetworkState.Available -> {
                    binding.tv.text = "Available"
                    SnackBarDisplay.removeNetworkUnavailableAlert()
                }
            }
        })
    }

}