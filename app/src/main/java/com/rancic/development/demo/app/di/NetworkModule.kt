package com.rancic.development.demo.app.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rancic.development.demo.app.BuildConfig
import com.rancic.development.demo.app.remote.api.CarApi
import com.rancic.development.demo.app.remote.network.AddHeaderInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
object NetworkModule {

    private const val REQUEST_TIMEOUT_S = 30L

    @Singleton
    @Provides
    fun provideCountriesRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        //.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
    fun provideOkClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(REQUEST_TIMEOUT_S, TimeUnit.SECONDS)
            .readTimeout(REQUEST_TIMEOUT_S, TimeUnit.SECONDS)
            .connectionPool(ConnectionPool(0, 5, TimeUnit.MINUTES))
            .protocols(listOf(Protocol.HTTP_1_1))
            .addNetworkInterceptor(AddHeaderInterceptor())
            .build()


    @Singleton
    @Provides
    fun provideUserRetrofitService(retrofit: Retrofit) : CarApi {
        return retrofit.create(CarApi::class.java)
    }

}