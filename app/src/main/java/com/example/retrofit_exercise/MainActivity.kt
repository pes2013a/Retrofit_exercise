package com.example.retrofit_exercise

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

const val url = "https://newsapi.org/v2/"
const val apiKey = "3cc6b034a31c416ea1a8f7b7d74065f8"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val newAPI = retrofit.create<NewsAPI>()
        val call: Call<News> = newAPI.getNewsHeadlinesCountry("jp", apiKey)

        val recyclerViewNews = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerViewNews.setHasFixedSize(true)
        recyclerViewNews.layoutManager = LinearLayoutManager(this)

        call.enqueue(
                object :
                        Callback<News> {

                    @SuppressLint("SetTextI18n")
                    override fun onResponse(call: Call<News>, response: Response<News>) {
                        if (response.isSuccessful) {
                            val recyclerViewNewsAdapter = RecyclerViewNewsAdapter(response.body()!!.articles)
                            recyclerViewNews.adapter = recyclerViewNewsAdapter
                        }
                    }

                    override fun onFailure(call: Call<News>, t: Throwable) = print(t.message)
                },
        )


    }
}