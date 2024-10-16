package com.example.tanveermidterm

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.appcompat.widget.Toolbar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val cityEditText = findViewById<EditText>(R.id.cityEditText)
        val submitButton = findViewById<Button>(R.id.submitButton)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val weatherImageView = findViewById<ImageView>(R.id.weatherImageView)

        val weatherTextView = findViewById<TextView>(R.id.weatherTextView)
        val tempMaxTextView = findViewById<TextView>(R.id.tempMaxTextView)
        val tempMinTextView = findViewById<TextView>(R.id.tempMinTextView)
        val descriptionTextView = findViewById<TextView>(R.id.descriptionTextView)
        val conditionsTextView = findViewById<TextView>(R.id.conditionsTextView)
        val precipProbTextView = findViewById<TextView>(R.id.precipProbTextView)

        submitButton.setOnClickListener {
            val city = cityEditText.text.toString()
            progressBar.visibility = View.VISIBLE
            viewModel.fetchWeather(city)
        }

        viewModel.weatherData.observe(this, Observer { weatherData ->
            progressBar.visibility = View.GONE
            if (weatherData != null) {
                val currentConditions = weatherData.currentConditions
                val today = weatherData.days[0]

                // Display current weather
                weatherTextView.text = "Current Temp: ${currentConditions.temp}°C"
                tempMaxTextView.text = "Max Temp: ${today.tempmax}°C"
                tempMinTextView.text = "Min Temp: ${today.tempmin}°C"
                descriptionTextView.text = "Description: ${today.description}"
                conditionsTextView.text = "Conditions: ${today.conditions}"
                precipProbTextView.text = "Precipitation Probability: ${today.precipprob}%"

                // Set the weather image based on conditions
                when (today.conditions.lowercase(Locale.getDefault())) {
                    "clear", "sunny" -> weatherImageView.setImageResource(R.drawable.sun)
                    "Partially cloudy" -> weatherImageView.setImageResource(R.drawable.clouds)
                    "rain", "rainy" -> weatherImageView.setImageResource(R.drawable.rainy)
                    else -> weatherImageView.setImageResource(R.drawable.weather)
                }
            } else {
                weatherTextView.text = "Error fetching weather"
            }
        })
    }
}
