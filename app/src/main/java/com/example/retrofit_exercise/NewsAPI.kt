package com.example.retrofit_exercise

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {
    @GET("top-headlines")
    fun getNewsHeadlinesCountry(@Query("country") country: String, @Query("apiKey") apiKey: String): Call<News>
}