package com.tezzin.dagger2.di.component

import com.tezzin.dagger2.MainActivity
import com.tezzin.dagger2.di.PerActivity
import com.tezzin.dagger2.di.module.ActivityModule
import com.tezzin.dagger2.utils.ClassIntro
import dagger.Component

/**
 * Created by claudiotezzin on 04/10/17.
 *
 */

@ClassIntro(
        author = "Claudio Tezzin",
        date = "04/10/2017",
        currentRevision = 1
)
@PerActivity
@Component(modules = arrayOf(ActivityModule::class), dependencies = arrayOf(ApplicationComponent::class))
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)
}