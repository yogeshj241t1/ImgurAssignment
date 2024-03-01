package com.imgur.assignment.data.model

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("id") var id: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("datetime") var datetime: Int? = null,
    @SerializedName("images_count") var imagesCount: Int? = null,
    @SerializedName("images") var images: ArrayList<Images> = arrayListOf(),
)