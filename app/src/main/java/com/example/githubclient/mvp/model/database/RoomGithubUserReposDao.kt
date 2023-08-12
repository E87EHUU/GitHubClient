package com.example.githubclient.mvp.model.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubclient.mvp.model.entity.RoomGithubUser
import com.example.githubclient.mvp.model.entity.RoomGithubUserRepos
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface RoomGithubUserReposDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: RoomGithubUserRepos): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<RoomGithubUserRepos>): Completable

    @Delete
    fun delete(user: RoomGithubUserRepos): Completable

    @Query("SELECT * FROM repos")
    fun getAll(): Single<List<RoomGithubUserRepos>>

    @Query("SELECT * FROM repos WHERE  userId = :userId")
    fun findForUser (userId: String): Single <List<RoomGithubUserRepos>>
}