package dev.studiocloud.instamovie.data.remote.response.tvResponse

import com.google.gson.annotations.SerializedName

class TvResponse {
    @SerializedName("page")
    var page = 0

    @SerializedName("total_pages")
    var totalPages = 0

    @SerializedName("results")
    var results: List<TvItem>? = null

    @SerializedName("total_results")
    var totalResults = 0
    override fun toString(): String {
        return "TvResponse{" +
                "page = '" + page + '\'' +
                ",total_pages = '" + totalPages + '\'' +
                ",results = '" + results + '\'' +
                ",total_results = '" + totalResults + '\'' +
                "}"
    }
}