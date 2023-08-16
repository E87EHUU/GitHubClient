package com.example.githubclient.di.modules

import com.example.githubclient.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App{
        return app
    }
}