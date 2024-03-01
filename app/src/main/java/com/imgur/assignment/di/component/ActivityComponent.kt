package com.imgur.assignment.di.component

import com.imgur.assignment.ui.topimages.TopWeeklyImagesActivity
import com.imgur.assignment.di.ActivityScope
import com.imgur.assignment.di.module.ActivityModule
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(activity: TopWeeklyImagesActivity)
}