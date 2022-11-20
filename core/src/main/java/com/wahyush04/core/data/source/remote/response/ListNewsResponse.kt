package com.wahyush04.core.data.source.remote.response

data class ListNewsResponse (
    val totalArticles: Int,
    val articles: List<NewsResponse>
)