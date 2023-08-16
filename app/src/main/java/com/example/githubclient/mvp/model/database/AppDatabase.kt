package com.example.githubclient.mvp.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.githubclient.mvp.model.entity.RoomGithubUser
import com.example.githubclient.mvp.model.entity.RoomGithubUserRepos
import java.lang.RuntimeException

@Database(
    entities = [RoomGithubUser::class, RoomGithubUserRepos::class],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): RoomGithubUserDao
    abstract fun reposDao(): RoomGithubUserReposDao

    companion object {
        const val DB_NAME = "github.db"
    }
}