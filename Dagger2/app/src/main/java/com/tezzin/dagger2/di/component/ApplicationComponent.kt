package com.tezzin.dagger2.di.component

import android.content.Context
import com.tezzin.dagger2.Dagger2Application
import com.tezzin.dagger2.data.DataManager
import com.tezzin.dagger2.data.DbHelper
import com.tezzin.dagger2.data.SharedPrefsHelper
import com.tezzin.dagger2.di.ApplicationContext
import com.tezzin.dagger2.di.module.ApplicationModule
import com.tezzin.dagger2.utils.ClassIntro
import dagger.Component
import javax.inject.Singleton

/**
 * Created by claudiotezzin on 04/10/17.
 *
 */

@ClassIntro(
        author = "Claudio Tezzin",
        date = "04/10/2017",
        currentRevision = 1
)
@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(dagger2Application: Dagger2Application)

    @ApplicationContext
    fun getContext(): Context

    fun getDataManager(): DataManager

    fun getSharedPrefs(): SharedPrefsHelper

    fun getDbHelper(): DbHelper
}