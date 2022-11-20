package com.wahyush04.core.data.source.remote

import android.util.Log
import com.wahyush04.core.data.source.remote.network.ApiResponse
import com.wahyush04.core.data.source.remote.network.ApiService
import com.wahyush04.core.data.source.remote.response.NewsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getList(): Flow<ApiResponse<List<NewsResponse>>> {
        return flow {
            try {
                apiService.getGnewsAndroid().also {
                    it.articles.apply {
                        if (isNotEmpty()){
                            emit(ApiResponse.Success(this))
                        }else{
                            emit(ApiResponse.Empty)
                        }
                    }
                }
            }catch (e: Exception){
                e.apply {
                    emit(ApiResponse.Error(toString()))
                    Log.e("RemoteDataSource", toString())
                }
            }
        }.flowOn(Dispatchers.IO)
    }

}