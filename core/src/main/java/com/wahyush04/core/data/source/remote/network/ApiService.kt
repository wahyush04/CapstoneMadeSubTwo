package com.wahyush04.core.data.source.remote.network

import com.wahyush04.core.Constant.API_KEY
import com.wahyush04.core.data.source.remote.response.ListNewsResponse
import retrofit2.http.GET

interface ApiService {

    @GET("search?q=android&token=$API_KEY&lang=en")
    suspend fun getGnewsAndroid(): ListNewsResponse
}