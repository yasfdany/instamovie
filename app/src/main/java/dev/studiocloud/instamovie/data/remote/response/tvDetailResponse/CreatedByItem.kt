package dev.studiocloud.instamovie.data.remote.response.tvDetailResponse

import com.google.gson.annotations.SerializedName

class CreatedByItem {
    @SerializedName("gender")
    var gender = 0

    @SerializedName("credit_id")
    var creditId: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("profile_path")
    var profilePath: String? = null

    @SerializedName("id")
    var id = 0
    override fun toString(): String {
        return "CreatedByItem{" +
                "gender = '" + gender + '\'' +
                ",credit_id = '" + creditId + '\'' +
                ",name = '" + name + '\'' +
                ",profile_path = '" + profilePath + '\'' +
                ",id = '" + id + '\'' +
                "}"
    }
}