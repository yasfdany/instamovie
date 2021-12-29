
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import dev.studiocloud.instamovie.data.local.LocalRepository
import dev.studiocloud.instamovie.data.remote.RemoteRepository
import dev.studiocloud.instamovie.data.remote.response.movieResponse.MovieResponse
import dev.studiocloud.instamovie.utils.FakeDummyData
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.validateMockitoUsage

class MainRepositoryTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val remote = mock(RemoteRepository::class.java)
    private val local = mock(LocalRepository::class.java)

    private val fakeMainRepository: FakeMainRepository? = FakeMainRepository.getInstance(remote, local)
    private val gson: Gson = Gson()
    private val movieResponse: MutableLiveData<MovieResponse> = MutableLiveData(gson.fromJson(FakeDummyData.jsonMovies, MovieResponse::class.java))

    @Test
    fun getMovies() {
        `when`(
            remote.getMovies(1, any())
        ).thenReturn(movieResponse)
    }

    @Test
    fun getTvs() {
    }

    @Test
    fun getMovieDetail() {
    }

    @Test
    fun getSimilarMovies() {
    }

    @After
    fun validate() {
        validateMockitoUsage()
    }
}