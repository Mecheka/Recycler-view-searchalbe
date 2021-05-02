package com.example.recyclerviewsearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CityViewModel: ViewModel() {

    private val _cityLiveData = MutableLiveData<List<CityData>>()
    val cityLiveData: LiveData<List<CityData>>
        get() = _cityLiveData

    init {
        val cityList = MockDataProvider.getCityDataList()
        _cityLiveData.value = cityList
    }
}