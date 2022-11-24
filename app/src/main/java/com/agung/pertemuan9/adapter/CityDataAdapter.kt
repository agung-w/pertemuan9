package com.agung.pertemuan9.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.agung.pertemuan9.R
import com.agung.pertemuan9.databinding.CityCardBinding
import com.agung.pertemuan9.entity.City

class CityDataAdapter(private val cities:ArrayList<City>) : RecyclerView.Adapter<CityDataAdapter.CityViewHolder>() {
    private lateinit var cityDataListener: CityDataListener
    inner class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val binding: CityCardBinding= CityCardBinding.bind(itemView)
        fun setData(city:City){
            binding.cityNameTV.text=city.name
            binding.cityCoordinateTV.text=city.coordinate.toString()
        }
    }
    interface CityDataListener{
        fun cardClicked(city: City)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.city_card,parent,false)
        return CityViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.setData(cities[position])
        holder.itemView.setOnClickListener {
            cityDataListener.cardClicked(cities[position])
        }
    }

    override fun getItemCount(): Int {
        return cities.size
    }
    fun setCityDataListener(cityDataListener: CityDataListener){
        this.cityDataListener=cityDataListener
    }
}