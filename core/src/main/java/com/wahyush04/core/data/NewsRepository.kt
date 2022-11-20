package com.wahyush04.core.data

import com.wahyush04.core.data.source.local.LocalDataSource
import com.wahyush04.core.data.source.remote.RemoteDataSource
import com.wahyush04.core.data.source.remote.network.ApiResponse
import com.wahyush04.core.data.source.remote.response.NewsResponse
import com.wahyush04.core.domain.model.News
import com.wahyush04.core.domain.repository.INewsRepository
import com.wahyush04.core.utils.AppExecutors
import com.wahyush04.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NewsRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : INewsRepository {

    override fun getAllList(): Flow<Resource<List<News>>> =
        object : NetworkBoundResource<List<News>, List<NewsResponse>>() {
            override fun loadFromDB(): Flow<List<News>> {
                return localDataSource.getList().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<News>?): Boolean = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<NewsResponse>>> =
                remoteDataSource.getList()

            override suspend fun saveCallResult(data: List<NewsResponse>) {
                val newsList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertNews(newsList)
            }
        }.asFlow()

    override fun getBookmarkList(): Flow<List<News>> {
        return localDataSource.getBookmarkNews().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setBookmarkList(news: News, state: Boolean) {
        val newsEntity = DataMapper.mapDomainToEntity(news)
        appExecutors.diskIO().execute { localDataSource.setBookmarkNews(newsEntity, state) }
    }
}