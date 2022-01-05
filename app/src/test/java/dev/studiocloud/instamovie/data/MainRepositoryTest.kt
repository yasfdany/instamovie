package dev.studiocloud.instamovie.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import dev.studiocloud.instamovie.data.local.LocalRepository
import dev.studiocloud.instamovie.data.remote.RemoteRepository
import dev.studiocloud.instamovie.data.remote.response.movieResponse.MovieResponse
import dev.studiocloud.instamovie.data.remote.response.tvResponse.TvResponse
import dev.studiocloud.instamovie.utils.FakeDummyData
import dev.studiocloud.instamovie.utils.LiveDataTestUtil
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class MainRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remoteRepository = mock(RemoteRepository::class.java)
    private val localRepository = mock(LocalRepository::class.java)
    private val fakeMainRepository = FakeMainRepository(remoteRepository, localRepository)

    private val dummyMovieResponse = Gson().fromJson(FakeDummyData.jsonMovies, MovieResponse::class.java)
    private val dummyTvResponse = Gson().fromJson(FakeDummyData.jsonTvs, TvResponse::class.java)

    @Test
    fun getMovies() {
        `when`(remoteRepository.getMovies(anyInt(), any())).thenAnswer {
            (it.arguments[1] as RemoteRepository.LoadMovieCallback).onAllMovieReceived(dummyMovieResponse)
            null
        }

        val result = LiveDataTestUtil.getValue(fakeMainRepository.getMovies(1){});
        verify(remoteRepository, times(1)).getMovies(anyInt(), any())
        assertEquals(dummyMovieResponse.results?.size, result?.results?.size);
    }

    @Test
    fun getTvs() {
        `when`(remoteRepository.getTvs(anyInt(), anyString(), any())).thenAnswer {
            (it.arguments[2] as RemoteRepository.LoadTvCallback).onAllTvReceived(dummyTvResponse)
            null
        }

        val result = LiveDataTestUtil.getValue(fakeMainRepository.getTvs(1, ""){})
        verify(remoteRepository, times(1)).getTvs(anyInt(), anyString(),any())
        assertEquals(dummyTvResponse.results?.size, result?.results?.size)
        assertEquals(dummyTvResponse.results?.first()?.id, result?.results?.first()?.id)
    }

    @Test
    fun getMovieDetail() {
    }

    @Test
    fun getSimilarMovies() {
    }

}