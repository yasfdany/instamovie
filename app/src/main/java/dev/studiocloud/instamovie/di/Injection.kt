package dev.studiocloud.instamovie.di

import android.app.Application
import dev.studiocloud.instamovie.data.MainRepository
import dev.studiocloud.instamovie.data.local.DatabaseClient
import dev.studiocloud.instamovie.data.local.LocalRepository
import dev.studiocloud.instamovie.data.remote.RemoteRepository


class Injection {
    companion object{
        fun provideRepository(application: Application): MainRepository? {
            val database: DatabaseClient? = DatabaseClient.getInstance(application)
            val localRepository = LocalRepository.getInstance(
                database?.getAppDatabase()!!.movieDao(),
                database.getAppDatabase()!!.tvDao(),
                database.getAppDatabase()!!.movieDetailDao(),
            )
            val remoteRepository = RemoteRepository.getInstance()

            return MainRepository.getInstance(remoteRepository!!, localRepository!!)
        }
    }
}
