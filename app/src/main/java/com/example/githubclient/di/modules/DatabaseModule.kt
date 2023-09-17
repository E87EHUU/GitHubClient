package com.example.githubclient.di.modules

import androidx.room.Room
import com.example.githubclient.App
import com.example.githubclient.mvp.model.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun database (app: App) = Room.databaseBuilder(
        app, AppDatabase::class.java,
        AppDatabase.DB_NAME
    ).build()
}