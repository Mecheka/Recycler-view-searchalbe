package com.example.recyclerviewsearch

import com.google.gson.annotations.SerializedName

data class CityData(
    @SerializedName("city_name") val cityName: String,
    @SerializedName("city_code") val cityCode: String
)