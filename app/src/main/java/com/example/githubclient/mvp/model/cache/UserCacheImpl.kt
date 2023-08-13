package com.example.githubclient.mvp.model.cache

import com.example.githubclient.mvp.model.database.AppDatabase
import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.mvp.model.entity.RoomGithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class UserCacheImpl(private val db:AppDatabase): IUserCache {

    override fun insertUsersToDb(githubUser: List<GithubUser>): Completable {
        return db.userDao().insertAll(githubUser.map { user ->
            RoomGithubUser(
                user.id,
                user.login,
                user.avatarUrl,
                user.reposUrl
            )
        })
    }

    override fun getUsersFromDb(): Single<List<GithubUser>> {
        return db.userDao().queryForAllUsers().map {
            it.map { roomUser ->
                GithubUser(
                    roomUser.id,
                    roomUser.login,
                    roomUser.avatarUrl,
                    roomUser.reposUrl
                )
            }
        }
    }
}