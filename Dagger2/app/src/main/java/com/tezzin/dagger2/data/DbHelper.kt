package com.tezzin.dagger2.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.tezzin.dagger2.di.ApplicationContext
import com.tezzin.dagger2.di.DatabaseInfo
import javax.inject.Inject
import android.content.ContentValues
import android.content.res.Resources
import android.database.Cursor
import com.tezzin.dagger2.utils.ClassIntro
import com.tezzin.dagger2.data.model.User
import java.sql.SQLException
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
class DbHelper @Inject constructor(@ApplicationContext private val context: Context,
                                   @DatabaseInfo private val  dbName: String,
                                   @DatabaseInfo private val  version: Int) : SQLiteOpenHelper(context, dbName, null, version) {

    object TABLE {
        val USER_TABLE_NAME = "users"
        val USER_COLUMN_USER_ID = "id"
        val USER_COLUMN_USER_NAME = "usr_name"
        val USER_COLUMN_USER_ADDRESS = "usr_add"
        val USER_COLUMN_USER_CREATED_AT = "created_at"
        val USER_COLUMN_USER_UPDATED_AT = "updated_at"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) tableCreateStatements(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (db != null) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE.USER_TABLE_NAME)
            onCreate(db)
        }
    }

    private fun tableCreateStatements(db: SQLiteDatabase) {
        try {
            db.execSQL(
                    "CREATE TABLE IF NOT EXISTS "
                            + TABLE.USER_TABLE_NAME + "("
                            + TABLE.USER_COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + TABLE.USER_COLUMN_USER_NAME + " VARCHAR(20), "
                            + TABLE.USER_COLUMN_USER_ADDRESS + " VARCHAR(50), "
                            + TABLE.USER_COLUMN_USER_CREATED_AT + " VARCHAR(10) DEFAULT " + getCurrentTimeStamp() + ", "
                            + TABLE.USER_COLUMN_USER_UPDATED_AT + " VARCHAR(10) DEFAULT " + getCurrentTimeStamp() + ")"
            )

        } catch (e: SQLException) {
            e.printStackTrace()
        }

    }

    @Throws(Resources.NotFoundException::class, NullPointerException::class)
    fun getUser(userId: Long?): User {
        var cursor: Cursor? = null
        try {
            val db = this.readableDatabase
            cursor = db.rawQuery(
                    ("SELECT * FROM "
                            + TABLE.USER_TABLE_NAME
                            + " WHERE "
                            + TABLE.USER_COLUMN_USER_ID
                            + " = ? "),
                    arrayOf((userId!!).toString() + ""))

            if (cursor != null && cursor.count > 0) {
                cursor.moveToFirst()
                return User(cursor.getLong(cursor.getColumnIndex(TABLE.USER_COLUMN_USER_ID)),
                        cursor.getString(cursor.getColumnIndex(TABLE.USER_COLUMN_USER_NAME)),
                        cursor.getString(cursor.getColumnIndex(TABLE.USER_COLUMN_USER_ADDRESS)),
                        cursor.getString(cursor.getColumnIndex(TABLE.USER_COLUMN_USER_CREATED_AT)),
                        cursor.getString(cursor.getColumnIndex(TABLE.USER_COLUMN_USER_UPDATED_AT)))
            } else {
                throw Resources.NotFoundException("User with id $userId does not exists")
            }
        } catch (e: NullPointerException) {
            e.printStackTrace()
            throw e
        } finally {
            if (cursor != null)
                cursor.close()
        }
    }

    @Throws(Exception::class)
    fun insertUser(user: User): Long? {
        try {
            val db = this.writableDatabase
            val contentValues = ContentValues()
            contentValues.put(TABLE.USER_COLUMN_USER_NAME, user.name)
            contentValues.put(TABLE.USER_COLUMN_USER_ADDRESS, user.address)
            return db.insert(TABLE.USER_TABLE_NAME, null, contentValues)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    private fun getCurrentTimeStamp(): String = (System.currentTimeMillis() / 1000).toString()
}