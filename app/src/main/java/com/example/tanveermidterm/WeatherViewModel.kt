package com.example.tanveermidterm

import WeatherData
import WeatherService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val _weatherData = MutableLiveData<WeatherData>()
    val weatherData: LiveData<WeatherData> = _weatherData

    private val weatherService: WeatherService = RetrofitInstance.weatherService

    fun fetchWeather(city: String) {
        viewModelScope.launch {
            try {
                val data = weatherService.getWeather(city)
                _weatherData.value = data
            } catch (e: Exception) {
                // Handle the error (e.g., show an error message)
                _weatherData.value = null
            }
        }
    }
}
