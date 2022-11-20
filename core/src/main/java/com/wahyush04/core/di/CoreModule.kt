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
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
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
        val passphrase: ByteArray = SQLiteDatabase.getBytes("wahyush04".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            GnewsDatabase::class.java, "gnews.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}


val networkModule = module {
    single {
        val hostname = "gnews.io"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/NJRIFvUE0mycD8zAoo80ZwdxkgP/9Jo0h4f9bQJ+5KQ=")
            .add(hostname, "sha256/jQJTbIh0grw0/1TkHSumWb+Fs0Ggogr621gT3PvPKG0=")
            .add(hostname, "sha256/C5+lpZ7tcVwmwQIMcRtPbsQtWLABXhQzejna0wHFr8M=")
            .add(hostname, "sha256/NJRIFvUE0mycD8zAoo80ZwdxkgP/9Jo0h4f9bQJ+5KQ=")
            .add(hostname, "sha256/jQJTbIh0grw0/1TkHSumWb+Fs0Ggogr621gT3PvPKG0=")
            .add(hostname, "sha256/C5+lpZ7tcVwmwQIMcRtPbsQtWLABXhQzejna0wHFr8M=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
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