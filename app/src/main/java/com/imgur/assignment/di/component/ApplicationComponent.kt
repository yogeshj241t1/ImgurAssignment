package com.imgur.assignment.di.component

import android.content.Context
import com.imgur.assignment.ImgurApplication
import com.imgur.assignment.data.api.NetworkService
import com.imgur.assignment.data.repository.TopSearchImageRepository
import com.imgur.assignment.data.repository.TopWeeklyImagesRepository
import com.imgur.assignment.di.ApplicationContext
import com.imgur.assignment.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: ImgurApplication)

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkService

    fun getTopWeeklyImagesRepository(): TopWeeklyImagesRepository

    fun getTopSearchImagesRepository(): TopSearchImageRepository
}