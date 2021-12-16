package dev.studiocloud.instamovie.data

import dev.studiocloud.instamovie.data.models.MovieData
import dev.studiocloud.instamovie.data.models.TvData

interface MainDataSource {
    fun getMovies(
        page: Int,
        onFinish : (data: MovieData?) -> Unit
    );

    fun getTvs(
        page: Int,
        onFinish : (data: TvData?) -> Unit
    );
}