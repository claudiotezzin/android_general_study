package com.tezzin.dagger2.di

import com.tezzin.dagger2.utils.ClassIntro
import javax.inject.Qualifier

/**
 * Created by claudiotezzin on 04/10/17.
 *
 */

@ClassIntro(
        author = "Claudio Tezzin",
        date = "04/10/2017",
        currentRevision = 1
)
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationContext