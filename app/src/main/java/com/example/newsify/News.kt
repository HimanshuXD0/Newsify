package com.example.newsify

data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)