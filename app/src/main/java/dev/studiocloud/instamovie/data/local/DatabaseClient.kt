package dev.studiocloud.instamovie.data.local

import android.content.Context
import androidx.room.Room
import dev.studiocloud.instamovie.data.local.room.AppDatabase

class DatabaseClient(context: Context) {
    private var appDatabase: AppDatabase? = null

    companion object{
        var mInstance: DatabaseClient? = null

        @Synchronized
        fun getInstance(mCtx: Context): DatabaseClient? {
            if (mInstance == null) {
                mInstance = DatabaseClient(mCtx)
            }
            return mInstance
        }
    }

    init {
        appDatabase = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "MovieCatalogue")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    fun getAppDatabase(): AppDatabase? {
        return appDatabase
    }

}