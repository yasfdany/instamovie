package dev.studiocloud.instamovie.data.remote.response.movieResponse

import com.google.gson.annotations.SerializedName

class MovieResponse {
    @SerializedName("page")
    var page = 0

    @SerializedName("total_pages")
    var totalPages = 0

    @SerializedName("results")
    var results: MutableList<MovieItem>? = null

    @SerializedName("total_results")
    var totalResults = 0
    override fun toString(): String {
        return "MovieResponse{" +
                "page = '" + page + '\'' +
                ",total_pages = '" + totalPages + '\'' +
                ",results = '" + results + '\'' +
                ",total_results = '" + totalResults + '\'' +
                "}"
    }
}