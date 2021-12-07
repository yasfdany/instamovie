package dev.studiocloud.instamovie.data.services.response.tvDetailResponse

import com.google.gson.annotations.SerializedName

class NetworksItem {
    @SerializedName("logo_path")
    var logoPath: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("id")
    var id = 0

    @SerializedName("origin_country")
    var originCountry: String? = null
    override fun toString(): String {
        return "NetworksItem{" +
                "logo_path = '" + logoPath + '\'' +
                ",name = '" + name + '\'' +
                ",id = '" + id + '\'' +
                ",origin_country = '" + originCountry + '\'' +
                "}"
    }
}