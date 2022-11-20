package com.wahyush04.core.utils

import com.wahyush04.core.data.source.local.entity.GnewsEntity
import com.wahyush04.core.data.source.remote.response.NewsResponse
import com.wahyush04.core.domain.model.News

object DataMapper {
    fun mapResponseToEntities(input: List<NewsResponse>): List<GnewsEntity>{
        val newsList = ArrayList<GnewsEntity>()
        input.map {
            val news = GnewsEntity(
                title = it.title,
                description = it.description,
                content = it.content,
                url = it.url,
                image = it.image,
                publishedAt = it.publishedAt,
                isBookmark = false
            )
            newsList.add(news)
        }
        return newsList
    }

    fun mapEntitiesToDomain(input: List<GnewsEntity>): List<News> {
        return input.map {
            News(
                title = it.title,
                description = it.description,
                content = it.content,
                url = it.url,
                image = it.image,
                publishedAt = it.publishedAt,
                isBookmark = it.isBookmark
            )
        }
    }

    fun mapDomainToEntity(input: News) = GnewsEntity(
        title = input.title,
        description =  input.description,
        content = input.content,
        url = input.url,
        image = input.image,
        publishedAt = input.publishedAt,
        isBookmark = input.isBookmark
    )
}