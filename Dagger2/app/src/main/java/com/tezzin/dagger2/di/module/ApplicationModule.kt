package com.tezzin.dagger2.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.tezzin.dagger2.di.ApplicationContext
import com.tezzin.dagger2.di.DatabaseInfo
import com.tezzin.dagger2.utils.ClassIntro
import dagger.Module
import dagger.Provides

/**
 * Created by claudiotezzin on 04/10/17.
 *
 */

@ClassIntro(
        author = "Claudio Tezzin",
        date = "04/10/2017",
        currentRevision = 1
)
@Module
class ApplicationModule(private val dagger2Application: Application) {

    @Provides
    @ApplicationContext
    fun provideContext(): Context = dagger2Application


    @Provides
    @DatabaseInfo
    fun provideDatabaseName(): String = "dagger2_study.db"

    @Provides
    @DatabaseInfo
    fun provideDatabaseVersion(): Int = 1

    @Provides
    fun provideSharedPreferences(): SharedPreferences =
            dagger2Application.getSharedPreferences("dagger2_study_prefs", Context.MODE_PRIVATE)
}