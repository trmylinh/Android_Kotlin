package com.example.sqlite_simple

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

//SQLiteOpenHelper()
// name: tên của database
// factory SQLiteDatabase.CursorFactory ? : sử dụng để tạo các đối tượng con trỏ hoặc null cho mặc định
// version: số của CSDL (bắt đầu từ 1)
// nếu CSDL cũ hơn, onUpgrade sẽ được sử dụng để nâng cấp CSDL
// nếu CSDL mới hơn, onDowngrade được sử dụng để hạ cấp CSDL
class MyDBHelper(context: Context): SQLiteOpenHelper(context, "USERDB", null, 1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        // tao bang
        p0?.execSQL("CREATE TABLE USERS(USERID Integer PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, PWD TEXT)")

        p0?.execSQL("INSERT INTO USERS(USERNAME, PWD) VALUES ('mylinh', 'linh123')")
        p0?.execSQL("INSERT INTO USERS(USERNAME, PWD) VALUES ('oppathai', 'thai123')")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}