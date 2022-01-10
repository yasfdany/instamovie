package dev.studiocloud.instamovie.di

import android.app.Application
import dev.studiocloud.instamovie.data.repository.MainRepository
import dev.studiocloud.instamovie.data.database.DatabaseClient
import dev.studiocloud.instamovie.data.repository.LocalRepository
import dev.studiocloud.instamovie.data.repository.RemoteRepository


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
