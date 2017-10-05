package com.tezzin.dagger2

import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho
import com.tezzin.dagger2.utils.ClassIntro
import com.tezzin.dagger2.di.component.ApplicationComponent
import com.tezzin.dagger2.di.component.DaggerApplicationComponent
import com.tezzin.dagger2.di.module.ApplicationModule


/**
 * Created by claudiotezzin on 04/10/17.
 *
 */

@ClassIntro(
        author = "Claudio Tezzin",
        date = "04/10/2017",
        currentRevision = 1
)
class Dagger2Application : Application() {

    private val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        applicationComponent.inject(this)

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

    fun getComponent(): ApplicationComponent = applicationComponent
}