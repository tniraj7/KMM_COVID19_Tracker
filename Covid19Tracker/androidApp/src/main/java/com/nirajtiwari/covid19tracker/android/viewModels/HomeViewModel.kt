package com.nirajtiwari.covid19tracker.android.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nirajtiwari.covid19tracker.API.CovidTrackingAPI
import com.nirajtiwari.covid19tracker.Model.Tracking
import com.nirajtiwari.covid19tracker.android.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _trackings = SingleLiveEvent<List<Tracking>>()
    val trackings: MutableLiveData<List<Tracking>>
        get() = _trackings

    private val _isLoading = SingleLiveEvent<Boolean>()
    val isLoading: MutableLiveData<Boolean>
        get() = _isLoading

    private val _isError = SingleLiveEvent<Boolean>()
    val isError: MutableLiveData<Boolean>
        get() = _isError

    init {
        getTrackingData()
    }

    fun getTrackingData() {
        isLoading.postValue(true)
        viewModelScope.launch {
            CovidTrackingAPI().fetchCovidTrackingData({
                _trackings.postValue(it)
                _isLoading.postValue(false)
                _isError.postValue(false)
            }, {
                _isError.postValue(true)
            })
        }
    }
}