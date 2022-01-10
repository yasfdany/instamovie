package dev.studiocloud.instamovie.data.model

import dev.studiocloud.instamovie.data.model.tvResponse.TvItem

class TvData(
    val page: Int,
    val maxPage: Int,
    val data: MutableList<TvItem>,
)