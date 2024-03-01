package com.imgur.assignment.data.model

import com.google.gson.annotations.SerializedName

data class ImgurDataResponse(
    @SerializedName("data") var data: ArrayList<Data> = arrayListOf(),
    @SerializedName("success") var success: Boolean? = null,
    @SerializedName("status") var status: Int? = null
)