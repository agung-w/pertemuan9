package com.agung.pertemuan9

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.agung.pertemuan9.adapter.CityDataAdapter
import com.agung.pertemuan9.databinding.ActivityMainBinding
import com.agung.pertemuan9.entity.City
import com.agung.pertemuan9.entity.CityWeather
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var cities:ArrayList<City>
    private lateinit var cityDataAdapter: CityDataAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cities=ArrayList()
        cityDataAdapter= CityDataAdapter(cities)
        binding.cityRV.layoutManager=LinearLayoutManager(this)
        binding.cityRV.adapter=cityDataAdapter
        cityDataAdapter.setCityDataListener(object:CityDataAdapter.CityDataListener{
            override fun cardClicked(city: City) {
                showCityWeather(city)
            }
        })
        binding.swipeRefreshLayout.setOnRefreshListener {
            this.fetchCityData()
            binding.swipeRefreshLayout.isRefreshing=false
        }
    }

    override fun onStart() {
        super.onStart()
        fetchCityData()
    }
    private fun fetchCityData(){
        val inputStream= assets.open("city.list.json")
        val reader=JsonReader(InputStreamReader(inputStream, Charsets.UTF_8))
        val gson= Gson()
        val cities=gson.fromJson<Array<City>>(reader,Array<City>::class.java)
        this.cities.clear()
        this.cities.addAll(cities)
        cityDataAdapter.notifyItemChanged(0)
    }
    private fun showCityWeather(city:City){
        val requestQueue= Volley.newRequestQueue(this)
        val uri= Uri.parse("https://api.openweathermap.org/data/2.5/weather").buildUpon()
            .appendQueryParameter("id",city.id.toString())
            .appendQueryParameter("appid","0a5d8b962d36a59b27de661a00fc13ae")
            .build()
        val request=StringRequest(Request.Method.GET,uri.toString(),
            {
                val cityWeather=Gson().fromJson<CityWeather>(it,CityWeather::class.java)
                Toast.makeText(this@MainActivity,cityWeather.main.tempMax.toString(),Toast.LENGTH_SHORT).show()
            },
            {
                Toast.makeText(this@MainActivity,it.message,Toast.LENGTH_SHORT).show()
            })
        requestQueue.add(request)
    }
}