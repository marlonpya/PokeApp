package com.example.pokeapp.data.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("url")
    val url: String,
    @SerializedName("name")
    val name: String,
) {
    val id: String
        get() = if (url.endsWith("/")) {
            url.dropLast(1).takeLastWhile { it.isDigit() }
        } else {
            url.takeLastWhile { it.isDigit() }
        }
}