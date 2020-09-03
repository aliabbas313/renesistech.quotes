package com.renesistech.quotes.mvp.data.local

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.renesistech.quotes.di.annotation.ApplicationContext

class DatabaseHelper constructor(@ApplicationContext context: Context) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    1
) {

    var classDB: SQLiteDatabase? = null

    override fun onCreate(db: SQLiteDatabase) {
        classDB = db
        db.execSQL("create table QUOTES " + "(_id text primary key,quoteText text,quoteAuthor text )")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        classDB = db
        db.execSQL("DROP TABLE IF EXISTS QUOTES")
        onCreate(db)
    }

    val team: Cursor
        get() {
            val db = this.readableDatabase
            val res = db.rawQuery("select * from QUOTES", null)
            res.moveToFirst()
            return res
        }

    fun getIndividualData(table: String, column: String, element: String): Cursor {
        val db = this.readableDatabase
        val res = db.rawQuery("select * from $table where $column=?", arrayOf(element))
        res.moveToFirst()
        return res
    }

    val teamName: Cursor
        get() {
            val db = this.readableDatabase
            val res = db.rawQuery("select USER_NAME from QUOTES", null)
            res.moveToFirst()
            return res
        }

    fun checkAvailability(table: String, column: String, element: String): Boolean {
        val db = this.readableDatabase
        val res = db.rawQuery("select * from $table where $column=?", arrayOf(element))
        res.moveToFirst()
        if (res.moveToFirst()) {
            res.close()
            return true
        }
        res.close()
        return false
    }

    fun checkSize(table: String, column: String, element: String): Int {
        val db = this.readableDatabase
        val res = db.rawQuery("select * from $table where $column=?", arrayOf(element))
        res.moveToFirst()
        return res.count
    }

    val isEmpty: Boolean
        get() {
            val db = this.writableDatabase
            val res = db.rawQuery("select * from QUOTES", null)
            res.moveToFirst()
            return if (res.moveToFirst()) {
                false
            } else true
        }

    fun insertQuote(
        _id: String?,
        quoteText: String?,
        quoteAuthor: String?
    ): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("_id", _id)
        contentValues.put("quoteText", quoteText)
        contentValues.put("quoteAuthor", quoteAuthor)
        db.insert("QUOTES", null, contentValues)
        return true
    }

    fun getTeamNameEmail(): Cursor? {
        val db = this.readableDatabase
        val res = db.rawQuery("select quoteText,quoteAuthor from QUOTES", null)
        res.moveToFirst()
        return res
    }

    fun clearAllQuotes(): Cursor? {
        val db = this.readableDatabase
        val res = db.rawQuery("delete from  QUOTES", null)
        res.moveToFirst()
        return res
    }
    fun insertReplaceQuote(
        _id: String?,
        quoteText: String?,
        quoteAuthor: String?
    ): Boolean {
        val db = this.writableDatabase
        db.execSQL("INSERT OR REPLACE INTO QUOTES(_id, quoteText, quoteAuthor)VALUES ('$_id', '$quoteText', '$quoteAuthor');")
        return true
    }

    companion object {
        const val DATABASE_NAME = "quotes_data.db"
    }
}