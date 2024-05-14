package com.rancic.development.demo.app.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rancic.development.demo.app.BuildConfig
import com.rancic.development.demo.app.local.AppDatabase
import com.rancic.development.demo.app.local.dao.CarDao
import com.rancic.development.demo.app.remote.api.CarApi
import com.rancic.development.demo.app.remote.network.AddHeaderInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object LocalModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DB_NAME)
            .build()

    @Provides
    fun provideCarDao(appDatabase: AppDatabase): CarDao = appDatabase.carDao()

}