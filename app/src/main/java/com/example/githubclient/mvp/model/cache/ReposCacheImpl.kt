package com.example.githubclient.mvp.model.cache

import com.example.githubclient.mvp.model.database.AppDatabase
import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.mvp.model.entity.GithubUserRepos
import com.example.githubclient.mvp.model.entity.RoomGithubUserRepos
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class ReposCacheImpl(private val db: AppDatabase) : IReposCache {

    override fun insertReposToDb(
        githubUserRepos: List<GithubUserRepos>,
        githubUser: GithubUser,
    ): Completable {
        return db.reposDao().insertAll(githubUserRepos.map { userRepos ->
            RoomGithubUserRepos(
                userRepos.id,
                userRepos.name,
                userRepos.forksCount,
                githubUser.id
            )
        })
    }

    override fun getReposFromDb(user: GithubUser): Single<List<GithubUserRepos>> {
        return db.reposDao().findForUser(user.id).map {
            it.map {roomUserRepos ->
                GithubUserRepos(
                    roomUserRepos.id,
                    roomUserRepos.name,
                    roomUserRepos.forksCount
                )
            }
        }
    }
}