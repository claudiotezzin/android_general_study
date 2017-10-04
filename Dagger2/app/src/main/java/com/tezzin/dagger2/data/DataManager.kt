package com.tezzin.dagger2.data

import android.content.Context
import android.content.res.Resources
import com.tezzin.dagger2.di.ApplicationContext
import com.tezzin.dagger2.utils.ClassIntro
import javax.inject.Inject
import javax.inject.Singleton
import com.tezzin.dagger2.data.model.User


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
class DataManager @Inject constructor(@ApplicationContext private val context: Context,
                                      private val dbHelper: DbHelper,
                                      private val sharedPrefsHelper: SharedPrefsHelper) {

    fun saveAccessToken(accessToken: String) {
        sharedPrefsHelper.put(SharedPrefsHelper.PREF_KEY_ACCESS_TOKEN, accessToken)
    }

    fun getAccessToken(): String =
            sharedPrefsHelper.get(SharedPrefsHelper.PREF_KEY_ACCESS_TOKEN, "")

    @Throws(Exception::class)
    fun createUser(user: User): Long? = dbHelper.insertUser(user)

    @Throws(Resources.NotFoundException::class, NullPointerException::class)
    fun getUser(userId: Long?): User = dbHelper.getUser(userId)
}