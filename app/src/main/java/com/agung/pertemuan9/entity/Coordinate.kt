package com.agung.pertemuan9.entity


import com.google.gson.annotations.SerializedName

data class Coordinate(
    @SerializedName("lat")
    val latitude: Double,
    @SerializedName("lon")
    val longitude: Double
){
    override fun toString(): String {
        return "{$latitude, $longitude}"
    }
}