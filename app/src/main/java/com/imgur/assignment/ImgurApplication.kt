package com.imgur.assignment

import android.app.Application
import com.imgur.assignment.di.component.ApplicationComponent
import com.imgur.assignment.di.module.ApplicationModule
import com.imgur.assignment.di.component.DaggerApplicationComponent

class ImgurApplication : Application() {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()

        applicationComponent.inject(this)
    }
}