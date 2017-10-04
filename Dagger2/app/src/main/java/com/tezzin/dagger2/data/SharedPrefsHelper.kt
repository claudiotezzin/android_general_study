package com.tezzin.dagger2.data

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton
import com.tezzin.dagger2.utils.ClassIntro


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
class SharedPrefsHelper @Inject constructor(private val sharedPreferences: SharedPreferences) {

    companion object {
        const val PREF_KEY_ACCESS_TOKEN = "access-token"
    }

    fun put(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun put(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    fun put(key: String, value: Float) {
        sharedPreferences.edit().putFloat(key, value).apply()
    }

    fun put(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun deleteSavedData(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }

    fun get(key: String, defaultValue: String): String =
            sharedPreferences.getString(key, defaultValue)

    fun get(key: String, defaultValue: Int): Int? =
            sharedPreferences.getInt(key, defaultValue)

    fun get(key: String, defaultValue: Float): Float? =
            sharedPreferences.getFloat(key, defaultValue)

    fun get(key: String, defaultValue: Boolean): Boolean? =
            sharedPreferences.getBoolean(key, defaultValue)
}
