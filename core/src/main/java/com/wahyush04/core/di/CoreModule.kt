package com.wahyush04.core.di

import androidx.room.Room
import com.wahyush04.core.Constant.BASE_URL
import com.wahyush04.core.data.NewsRepository
import com.wahyush04.core.data.source.local.LocalDataSource
import com.wahyush04.core.data.source.local.room.GnewsDatabase
import com.wahyush04.core.data.source.remote.RemoteDataSource
import com.wahyush04.core.data.source.remote.network.ApiService
import com.wahyush04.core.domain.repository.INewsRepository
import com.wahyush04.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<GnewsDatabase>().gnewshDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            GnewsDatabase::class.java, "gnews.db"
        ).fallbackToDestructiveMigration().build()
    }
}


val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<INewsRepository>{
        NewsRepository(
            get(),
            get(),
            get()
        )
    }
}