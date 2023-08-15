package com.example.weatherapp

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Website.URL
import android.util.Log
import android.view.View
import com.example.weatherapp.databinding.ActivityMainBinding
import io.github.cdimascio.dotenv.dotenv
import org.json.JSONObject
import java.net.*
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    val CITY: String = "HaNoi"
    val dotenv = dotenv {
        directory = "/assets"
        filename = "env" // instead of 'env', use 'env'
    }

    inner class WeatherClass(): AsyncTask<String, Void, String>(){
        override fun doInBackground(vararg p0: String?): String? {
            var response: String?
            try{
                response = URL("https://api.openweathermap.org/data/2.5/weather?q=$CITY&units=metric&appid=${dotenv["API_KEY"]}")
                    .readText(Charsets.UTF_8)
            }
            catch (e: Exception){
                response = null
            }
            return response
        }

        override fun onPreExecute() {
            super.onPreExecute()
            binding.loader.visibility = View.VISIBLE
            binding.mainContainer.visibility = View.GONE
            binding.errortext.visibility = View.GONE
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try{
                val jsonObj = JSONObject(result)
                val main = jsonObj.getJSONObject("main")
                val sys = jsonObj.getJSONObject("sys")
                val wind = jsonObj.getJSONObject("wind")
                val weather = jsonObj.getJSONArray("weather").getJSONObject(0)
                val updatedAt: Long = jsonObj.getLong("dt")
                val updatedAtText = "Updated at ${SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(
                    Date(updatedAt*1000)
                )}"
                val temp = main.getString("temp")+"°C"
                val tempMin = "Min Temp: ${main.getString("temp_min")} °C"
                val tempMax = "Max Temp: ${main.getString("temp_max")} °C"
                val pressure = main.getString("pressure")
                val humidity = main.getString("humidity")
                val sunrise: Long = sys.getLong("sunrise")
                val sunset: Long = sys.getLong("sunset")
                val windSpeed = wind.getString("speed")
                val weatherDescription = weather.getString("description")
                val address = jsonObj.getString("name") + ", " + sys.getString("country")

                binding.address.text = address
                binding.updatedAt.text = updatedAtText
                binding.status.text = weatherDescription.capitalize()
                binding.temp.text = temp
                binding.tempMin.text = tempMin
                binding.tempMax.text = tempMax
                binding.sunrise.text = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(
                    Date(updatedAt*1000))
                binding.sunset.text = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(
                    Date(updatedAt*1000))
                binding.wind.text = windSpeed
                binding.pressure.text = pressure
                binding.humidity.text = humidity

                binding.loader.visibility = View.GONE
                binding.mainContainer.visibility = View.VISIBLE
            }
            catch (e: Exception){
                binding.loader.visibility = View.GONE
                binding.errortext.visibility = View.VISIBLE
            }
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        WeatherClass().execute()
    }
}