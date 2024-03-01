package com.imgur.assignment.data.repository

import android.util.Log
import com.google.gson.Gson
import com.imgur.assignment.data.api.NetworkService
import com.imgur.assignment.data.model.Data
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopWeeklyImagesRepository @Inject constructor(private val networkService: NetworkService) {

    fun getTopWeeklyImages(): Flow<List<Data>> {
        return flow {
            emit(networkService.getTopWeeklyImages())
            Log.e("Data", Gson().toJson(networkService.getTopWeeklyImages()))
        }.map { it.data }
    }
}