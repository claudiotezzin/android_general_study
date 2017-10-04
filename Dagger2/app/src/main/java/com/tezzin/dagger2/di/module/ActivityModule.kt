package com.tezzin.dagger2.di.module

import android.content.Context
import com.tezzin.dagger2.MainActivity
import com.tezzin.dagger2.di.ActivityContext
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
class ActivityModule(private val mainActivity: MainActivity) {

    @Provides
    @ActivityContext
    fun provideContext(): Context = mainActivity
}