package com.example.retrofit_exercise

class News(val articles: List<Article>, val status: String, val totalResults: Int)
class Article(
        val source: SourceModel,
        val author: String,
        val title: String,
        val description: String,
        val url: String,
        val urlToImage: String,
        val publishedAt: String,
        val content: String,
)

class SourceModel(val name: String, val id: String)
