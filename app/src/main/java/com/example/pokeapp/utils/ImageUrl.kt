package com.example.pokeapp.utils

import com.example.pokeapp.data.datasource.remote.ApiURL

object ImageUrl {

    fun get(id: String) = ApiURL.IMAGE_URL.replace("@", id)
}