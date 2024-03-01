package com.imgur.assignment.data.api

import com.imgur.assignment.data.model.ImgurDataResponse
import com.imgur.assignment.utils.AppConstant.AUTH_KEY
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @Headers("Authorization: $AUTH_KEY")
    @GET("3/gallery/top/top/week/1")
    suspend fun getTopWeeklyImages(): ImgurDataResponse

    @Headers("Authorization: $AUTH_KEY")
    @GET("3/gallery/search")
    suspend fun getSearchImage(@Query("q") searchImage: String): ImgurDataResponse
}