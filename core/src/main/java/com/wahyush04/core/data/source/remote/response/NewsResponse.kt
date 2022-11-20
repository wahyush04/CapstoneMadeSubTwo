package com.wahyush04.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class NewsResponse (
    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("content")
    val content: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("image")
    val image: String,

    @SerializedName("publishedAt")
    val publishedAt: String
)