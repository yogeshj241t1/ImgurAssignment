package com.imgur.assignment.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.imgur.assignment.data.repository.TopSearchImageRepository
import com.imgur.assignment.data.repository.TopWeeklyImagesRepository
import com.imgur.assignment.di.ActivityContext
import com.imgur.assignment.ui.base.ViewModelProviderFactory
import com.imgur.assignment.ui.topimages.TopSearchImageViewModel
import com.imgur.assignment.ui.topimages.TopWeeklyImagesAdapter
import com.imgur.assignment.ui.topimages.TopWeeklyImagesViewModel
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @ActivityContext
    @Provides
    fun provideContext(): Context {
        return activity
    }

    @Provides
    fun provideTopWeeklyImagesViewModel(topWeeklyImagesRepository: TopWeeklyImagesRepository): TopWeeklyImagesViewModel {
        return ViewModelProvider(
            activity,
            ViewModelProviderFactory(TopWeeklyImagesViewModel::class)
            {
                TopWeeklyImagesViewModel(topWeeklyImagesRepository)
            }
        )[TopWeeklyImagesViewModel::class.java]
    }

    @Provides
    fun provideImageSearchViewModel(topSearchImageRepository: TopSearchImageRepository): TopSearchImageViewModel {
        return ViewModelProvider(
            activity,
            ViewModelProviderFactory(TopSearchImageViewModel::class)
            {
                TopSearchImageViewModel(topSearchImageRepository)
            }
        )[TopSearchImageViewModel::class.java]
    }

    @Provides
    fun provideTopWeeklyImagesAdapter() = TopWeeklyImagesAdapter(ArrayList())

}