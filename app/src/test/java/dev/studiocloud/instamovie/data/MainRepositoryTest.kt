package dev.studiocloud.instamovie.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import dev.studiocloud.instamovie.data.local.LocalRepository
import dev.studiocloud.instamovie.data.remote.RemoteRepository
import dev.studiocloud.instamovie.data.remote.RemoteRepository.LoadMovieCallback
import dev.studiocloud.instamovie.data.remote.RemoteRepository.LoadTvCallback
import dev.studiocloud.instamovie.data.remote.response.movieResponse.MovieResponse
import dev.studiocloud.instamovie.data.remote.response.tvResponse.TvResponse
import dev.studiocloud.instamovie.utils.FakeDummyData
import dev.studiocloud.instamovie.utils.LiveDataTestUtil
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.doAnswer
import org.mockito.Mockito.mock

class MainRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var remoteRepository: RemoteRepository
    private lateinit var localRepository: LocalRepository

    private lateinit var fakeMainRepository: FakeMainRepository

    private val dummyMovieResponse = Gson().fromJson(FakeDummyData.jsonMovies, MovieResponse::class.java)
    private val dummyTvResponse = Gson().fromJson(FakeDummyData.jsonTvs, TvResponse::class.java)

    @Before
    fun setUp() {
        remoteRepository = mock(RemoteRepository::class.java)
        localRepository = mock(LocalRepository::class.java)
        fakeMainRepository = FakeMainRepository.getInstance(remoteRepository, localRepository)!!
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getMovies() {
        doAnswer {
            (it.arguments[1] as LoadMovieCallback)
                .onAllMovieReceived(dummyMovieResponse)
            null
        }.`when`(remoteRepository).getMovies(1, null)

        val result = LiveDataTestUtil.getValue(fakeMainRepository.getMovies(1){});
        assertEquals(dummyMovieResponse.results?.size, result?.results?.size);
    }

    @Test
    fun getTvs() {
        doAnswer {
            (it.arguments[2] as LoadTvCallback)
                .onAllTvReceived(dummyTvResponse)
            null
        }.`when`(remoteRepository).getTvs(1,"", null)

        val result = LiveDataTestUtil.getValue(fakeMainRepository.getTvs(1, ""){})
        assertEquals(dummyTvResponse.results?.size, result?.results?.size)
    }

    @Test
    fun getMovieDetail() {
    }

    @Test
    fun getSimilarMovies() {
    }
}