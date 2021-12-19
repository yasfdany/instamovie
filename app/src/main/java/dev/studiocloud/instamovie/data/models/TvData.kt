package dev.studiocloud.instamovie.data.models

import dev.studiocloud.instamovie.data.remote.response.tvResponse.TvItem

class TvData(
    val page: Int,
    val maxPage: Int,
    val data: MutableList<TvItem>,
)