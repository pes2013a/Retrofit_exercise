package com.example.retrofit_exercise

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import retrofit2.converter.gson.GsonConverterFactory
import com.example.retrofit_exercise.NewsAPI
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory.create

const val url = "https://newsapi.org/v2/"
const val apiKey = "3cc6b034a31c416ea1a8f7b7d74065f8"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView = findViewById<TextView>(R.id.textView)

        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val newAPI = retrofit.create<NewsAPI>()
        val call: Call<News> = newAPI.getNewsHeadlinesCountry("jp", apiKey)

        call.enqueue(
                object :
                        Callback<News> {

                    @SuppressLint("SetTextI18n")
                    override fun onResponse(call: Call<News>, response: Response<News>) {
                        if (!response.isSuccessful) textView.text = response.code().toString()
                        else {
                            response.body()!!
                                    .articles.forEach{
                                            textView.text = textView.text.toString() +
                                                    "author: ${it.author}\n" +
                                                    "title: ${it.title}\n" +
                                                    "description: ${it.description}\n" +
                                                    "publishedAt: ${it.publishedAt}\n\n"

                                        }
                                        textView.text= textView.text.toString() + "\n\n"

                        }


                    }

                    override fun onFailure(call: Call<News>, t: Throwable) {
                        textView.text = t.message
                    }


                },
        )


    }
}