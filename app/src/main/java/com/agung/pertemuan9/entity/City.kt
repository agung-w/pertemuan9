package com.agung.pertemuan9.entity


import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("coord")
    val coordinate: Coordinate,
    @SerializedName("country")
    val country: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("state")
    val state: String
)