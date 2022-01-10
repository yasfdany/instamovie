package dev.studiocloud.instamovie.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import dev.studiocloud.instamovie.data.repository.LocalRepository
import dev.studiocloud.instamovie.data.repository.RemoteRepository
import dev.studiocloud.instamovie.data.model.movieDetailResponse.MovieDetailData
import dev.studiocloud.instamovie.data.model.movieResponse.MovieResponse
import dev.studiocloud.instamovie.data.model.similarMovieResponse.SimilarMovieResponse
import dev.studiocloud.instamovie.data.model.tvResponse.TvResponse
import dev.studiocloud.instamovie.utils.FakeDummyData
import dev.studiocloud.instamovie.utils.LiveDataTestUtil
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.`when`
import org.mockito.kotlin.*

class MainRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remoteRepository = mock<RemoteRepository>()
    private val localRepository = mock<LocalRepository>()
    private val fakeMainRepository = FakeMainRepository(remoteRepository, localRepository)

    private val dummyMovieResponse = Gson().fromJson(FakeDummyData.jsonMovies, MovieResponse::class.java)
    private val dummyTvResponse = Gson().fromJson(FakeDummyData.jsonTvs, TvResponse::class.java)
    private val dummySpiderManTvResponse = Gson().fromJson(FakeDummyData.jsonSpiderManTv, TvResponse::class.java)
    private val dummySpiderManDetailResponse = Gson().fromJson(FakeDummyData.jsonSpiderManDetail, MovieDetailData::class.java)
    private val dummySimilarMovieResponse = Gson().fromJson(FakeDummyData.jsonSimilarMovie, SimilarMovieResponse::class.java)

    @Test
    fun `Test get all movie list`() {
        `when`(remoteRepository.getMovies(anyInt(), any())).thenAnswer {
            (it.arguments[1] as RemoteRepository.LoadMovieCallback).onAllMovieReceived(dummyMovieResponse)
            null
        }

        val result = LiveDataTestUtil.getValue(fakeMainRepository.getMovies(1){});
        verify(remoteRepository, times(1)).getMovies(anyInt(), any())
        assertEquals(dummyMovieResponse.results?.size, result?.results?.size);
        assertEquals(dummyMovieResponse.results?.first()?.id, result?.results?.first()?.id);
    }

    @Test
    fun `Test getTv with empty search query`() {
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
    fun `Test getTv with spiderman search query`() {
        `when`(remoteRepository.getTvs(eq(1), eq("spiderman"), any())).thenAnswer {
            (it.arguments[2] as RemoteRepository.LoadTvCallback).onAllTvReceived(dummySpiderManTvResponse)
            null
        }

        val result = LiveDataTestUtil.getValue(fakeMainRepository.getTvs(1, "spiderman"){})
        verify(remoteRepository, times(1)).getTvs(eq(1), eq("spiderman"),any())
        assertEquals(dummySpiderManTvResponse.results?.size, result?.results?.size)
        assertEquals(dummySpiderManTvResponse.results?.first()?.id, result?.results?.first()?.id)
    }

    @Test
    fun `Test detail movie if it match with the movie`() {
        `when`(remoteRepository.getMovieDetail(eq(634649), any())).thenAnswer {
            (it.arguments[1] as RemoteRepository.LoadDetailMovieCallback).onDetailMovieReceived(dummySpiderManDetailResponse)
            null
        }

        val result = LiveDataTestUtil.getValue(fakeMainRepository.getMovieDetail(634649){})
        verify(remoteRepository, times(1)).getMovieDetail(eq(634649), any())
        assertEquals(dummySpiderManDetailResponse.id,result?.id)
        assertEquals(dummySpiderManDetailResponse.title,result?.title)
    }

    @Test
    fun `Test similar movie in spiderman`() {
        `when`(remoteRepository.getSimilarMovies(eq(634649), any())).thenAnswer {
            (it.arguments[1] as RemoteRepository.LoadSimilarMovieCallback).onSimilarMovieReceived(dummySimilarMovieResponse)
            null
        }

        val result = LiveDataTestUtil.getValue(fakeMainRepository.getSimilarMovies(634649){})
        verify(remoteRepository, times(1)).getSimilarMovies(eq(634649), any())
        assertEquals(dummySimilarMovieResponse.results?.size, result?.results?.size)
        assertEquals(dummySimilarMovieResponse.results?.first()?.id, result?.results?.first()?.id)
    }
}