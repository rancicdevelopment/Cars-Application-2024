package com.rancic.development.demo.app.di

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import com.rancic.development.demo.app.repository.CarRemoteDataSource
import com.rancic.development.demo.app.repository.CarRepository
import com.rancic.development.demo.app.repository.CarRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideTicketRepository(
        dataSource: CarRemoteDataSource, ioDispatcher: CoroutineDispatcher
    ) : CarRepository = CarRepositoryImpl(dataSource, ioDispatcher)

    @Singleton
    @Provides
    fun provideContext(@ApplicationContext context: Context): Context = context

    @Singleton
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Singleton
    @Provides
    fun provideResources(context: Context): Resources = context.resources

    @Singleton
    @Provides
    fun provideSharedPrefs(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)


}