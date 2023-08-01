package com.example.sqlite_crud

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDBHelper(context: Context ): SQLiteOpenHelper(context, "sqlite_crud", null,1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table USER (_id integer primary key autoincrement, username text, email text)")

        p0?.execSQL("insert into USER(username, email) values ('mylinh', 'linh@gmail.com')")
        p0?.execSQL("insert into USER(username, email) values ('haanh', 'anh@gmail.com')")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}