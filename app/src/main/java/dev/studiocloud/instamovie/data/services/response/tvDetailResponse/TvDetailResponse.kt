package dev.studiocloud.instamovie.data.services.response.tvDetailResponse

import com.google.gson.annotations.SerializedName

class TvDetailResponse {
    @SerializedName("original_language")
    var originalLanguage: String? = null

    @SerializedName("number_of_episodes")
    var numberOfEpisodes = 0

    @SerializedName("networks")
    var networks: List<NetworksItem>? = null

    @SerializedName("type")
    var type: String? = null

    @SerializedName("backdrop_path")
    var backdropPath: String? = null

    @SerializedName("genres")
    var genres: List<GenresItem>? = null

    @SerializedName("popularity")
    var popularity = 0.0

    @SerializedName("id")
    var id = 0

    @SerializedName("number_of_seasons")
    var numberOfSeasons = 0

    @SerializedName("vote_count")
    var voteCount = 0

    @SerializedName("first_air_date")
    var firstAirDate: String? = null

    @SerializedName("overview")
    var overview: String? = null

    @SerializedName("seasons")
    var seasons: List<SeasonsItem>? = null

    @SerializedName("languages")
    var languages: List<String>? = null

    @SerializedName("created_by")
    var createdBy: List<CreatedByItem>? = null

    @SerializedName("last_episode_to_air")
    var lastEpisodeToAir: LastEpisodeToAir? = null

    @SerializedName("poster_path")
    var posterPath: String? = null

    @SerializedName("origin_country")
    var originCountry: List<String>? = null

    @SerializedName("production_companies")
    var productionCompanies: List<ProductionCompaniesItem>? = null

    @SerializedName("original_name")
    var originalName: String? = null

    @SerializedName("vote_average")
    var voteAverage = 0.0

    @SerializedName("name")
    var name: String? = null

    @SerializedName("episode_run_time")
    var episodeRunTime: List<Int>? = null

    @SerializedName("next_episode_to_air")
    var nextEpisodeToAir: Any? = null

    @SerializedName("in_production")
    var isInProduction = false

    @SerializedName("last_air_date")
    var lastAirDate: String? = null

    @SerializedName("homepage")
    var homepage: String? = null

    @SerializedName("status")
    var status: String? = null
    override fun toString(): String {
        return "TvDetailResponse{" +
                "original_language = '" + originalLanguage + '\'' +
                ",number_of_episodes = '" + numberOfEpisodes + '\'' +
                ",networks = '" + networks + '\'' +
                ",type = '" + type + '\'' +
                ",backdrop_path = '" + backdropPath + '\'' +
                ",genres = '" + genres + '\'' +
                ",popularity = '" + popularity + '\'' +
                ",id = '" + id + '\'' +
                ",number_of_seasons = '" + numberOfSeasons + '\'' +
                ",vote_count = '" + voteCount + '\'' +
                ",first_air_date = '" + firstAirDate + '\'' +
                ",overview = '" + overview + '\'' +
                ",seasons = '" + seasons + '\'' +
                ",languages = '" + languages + '\'' +
                ",created_by = '" + createdBy + '\'' +
                ",last_episode_to_air = '" + lastEpisodeToAir + '\'' +
                ",poster_path = '" + posterPath + '\'' +
                ",origin_country = '" + originCountry + '\'' +
                ",production_companies = '" + productionCompanies + '\'' +
                ",original_name = '" + originalName + '\'' +
                ",vote_average = '" + voteAverage + '\'' +
                ",name = '" + name + '\'' +
                ",episode_run_time = '" + episodeRunTime + '\'' +
                ",next_episode_to_air = '" + nextEpisodeToAir + '\'' +
                ",in_production = '" + isInProduction + '\'' +
                ",last_air_date = '" + lastAirDate + '\'' +
                ",homepage = '" + homepage + '\'' +
                ",status = '" + status + '\'' +
                "}"
    }
}