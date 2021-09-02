package com.nirajtiwari.covid19tracker.android.views

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import com.nirajtiwari.covid19tracker.android.R
import com.nirajtiwari.covid19tracker.android.databinding.ActivityMainBinding
import com.nirajtiwari.covid19tracker.android.viewModels.HomeViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observerViewModel()
        setupSwipeDownToRefresh()
        onClickRefreshButton()

        //AppCenter
        AppCenter.start(
            application, "d1ceeece-2754-4a32-affa-b43e8ffceba3",
            Analytics::class.java, Crashes::class.java
        )
    }

    private fun setupSwipeDownToRefresh() {
        binding.swipeRefreshControl.apply {
            setProgressBackgroundColorSchemeColor(resources.getColor(R.color.colorPrimary))
            setColorSchemeColors(Color.WHITE)
            setOnRefreshListener {
                isRefreshing = false
                viewModel.getTrackingData()
            }
        }
    }

    private fun onClickRefreshButton() {
        binding.menuRefresh.setOnClickListener {
            viewModel.getTrackingData()
        }
    }

    private fun observerViewModel() {
        viewModel.trackings.observe(this, {
            binding.covidTrackerList.apply {
                adapter = CovidTrackerAdapters(it)
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
        })

        viewModel.isLoading.observe(this, { isLoading ->
            binding.swipeRefreshControl.visibility = if (isLoading) View.GONE else View.VISIBLE
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })

        viewModel.isError.observe(this, { isError ->
            binding.errorMessage.visibility = if (isError) View.VISIBLE else View.GONE
        })
    }
}
