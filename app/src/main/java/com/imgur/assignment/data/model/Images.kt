package com.imgur.assignment.data.model

import com.google.gson.annotations.SerializedName


data class Images(
    @SerializedName("id") var id: String? = null,
    @SerializedName("link") var link: String? = null,
)