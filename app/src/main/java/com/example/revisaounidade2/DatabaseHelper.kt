package com.example.revisaounidade2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val sql = """
            CREATE TABLE $TABLE_NAME (
                id INTEGER PRIMARY KEY,
                title TEXT NOT NULL,
                content TEXT
            );
        """.trimIndent()
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    companion object {
        const val DATABASE_NAME = "revisao"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "posts"
    }
}
