package com.example.tanveermidterm

import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {
    @GET("VisualCrossingWebServices/rest/services/timeline/{city}?unitGroup=metric&key=BPJQSLAY5H2YXY2VXQJ75PKDL&contentType=json")
    suspend fun getWeather(@Path("city") city: String): WeatherData
}
