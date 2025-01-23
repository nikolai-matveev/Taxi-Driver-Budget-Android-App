package ru.claus42.taxidriverbudget.application.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

typealias ApplicationContext = Context

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun applicationContext(application: Application): ApplicationContext =
        application.applicationContext
}