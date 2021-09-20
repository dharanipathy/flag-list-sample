package com.example.flaglist.model

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("name")
    val countryName: String?,

    @SerializedName("capital")
    val countryCapital: String?,

    @SerializedName("flagPNG")
    val flagUri: String?
)
