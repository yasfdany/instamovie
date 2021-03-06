package dev.studiocloud.instamovie.data.repository

import dev.studiocloud.instamovie.data.database.entity.Movie
import dev.studiocloud.instamovie.data.database.entity.MovieDetail
import dev.studiocloud.instamovie.data.database.entity.Tv
import dev.studiocloud.instamovie.data.database.room.dao.MovieDao
import dev.studiocloud.instamovie.data.database.room.dao.MovieDetailDao
import dev.studiocloud.instamovie.data.database.room.dao.TvDao

open class LocalRepository(
    private val movieDao: MovieDao,
    private val tvDao: TvDao,
    private val movieDetailDao: MovieDetailDao,
) {
    companion object{
        private var INSTANCE: LocalRepository? = null

        fun getInstance(
            movieDao: MovieDao,
            tvDao: TvDao,
            movieDetailDao: MovieDetailDao,
        ): LocalRepository? {
            if (INSTANCE == null) {
                INSTANCE =
                    LocalRepository(movieDao, tvDao, movieDetailDao)
            }
            return INSTANCE
        }
    }

    fun getAllMovie(): MutableList<Movie> {
        return movieDao.allData
    }

    fun insertMovie(movie: Movie?) {
        movieDao.insertData(movie)
    }

    fun insertAllMovie(movies: MutableList<Movie>) {
        movieDao.insertAllData(movies)
    }

    fun deleteMovie(movie: Movie?) {
        movieDao.deleteData(movie)
    }

    fun updateMovie(movie: Movie?) {
        movieDao.updateData(movie)
    }

    fun searchTv(query: String): MutableList<Tv> {
        return tvDao.searchData(query)
    }

    fun getAllTv(): MutableList<Tv> {
        return tvDao.allData
    }

    fun insertTv(tv: Tv?) {
        tvDao.insertData(tv)
    }

    fun insertAllTv(tvs: MutableList<Tv>) {
        tvDao.insertAllData(tvs)
    }

    fun deleteTv(tv: Tv?) {
        tvDao.deleteData(tv)
    }

    fun updateTv(tv: Tv?) {
        tvDao.updateData(tv)
    }

    fun getMovieDetailById(id: Int): MutableList<MovieDetail> {
        return movieDetailDao.getData(id)
    }

    fun insertMovieDetail(movieDetail: MovieDetail?) {
        movieDetailDao.insertData(movieDetail)
    }

    fun deleteMovieDetail(movieDetail: MovieDetail?) {
        movieDetailDao.deleteData(movieDetail)
    }

    fun updateMovieDetail(movieDetail: MovieDetail?) {
        movieDetailDao.updateData(movieDetail)
    }

}