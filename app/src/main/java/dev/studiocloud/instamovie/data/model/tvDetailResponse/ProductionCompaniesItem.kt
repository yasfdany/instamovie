package dev.studiocloud.instamovie.data.model.tvDetailResponse

import com.google.gson.annotations.SerializedName

class ProductionCompaniesItem {
    @SerializedName("logo_path")
    var logoPath: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("id")
    var id = 0

    @SerializedName("origin_country")
    var originCountry: String? = null
    override fun toString(): String {
        return "ProductionCompaniesItem{" +
                "logo_path = '" + logoPath + '\'' +
                ",name = '" + name + '\'' +
                ",id = '" + id + '\'' +
                ",origin_country = '" + originCountry + '\'' +
                "}"
    }
}