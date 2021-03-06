package dev.studiocloud.instamovie.data.model.similarMovieResponse

import com.google.gson.annotations.SerializedName
import dev.studiocloud.instamovie.data.model.movieResponse.MovieItem

data class SimilarMovieResponse(
	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("results")
	val results: MutableList<MovieItem>? = null,

	@field:SerializedName("total_results")
	val totalResults: Int? = null
)