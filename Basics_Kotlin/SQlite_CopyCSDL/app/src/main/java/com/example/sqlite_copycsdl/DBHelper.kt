package com.example.sqlite_copycsdl

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import java.io.File
import java.io.FileOutputStream

class DBHelper(private val context: Context) {
    companion object {
        private val DB_NAME = "tuhoc.db"
    }

    fun openDatabase(): SQLiteDatabase {
        val dbFile = context.getDatabasePath(DB_NAME)
        val file = File(dbFile.toString())
        Log.d("dbFile.path",dbFile.toString())
        // chua ton tai file
        if (!file.exists()) {
            copyDatabase(dbFile)
        }
        else{
            Log.d("error","DB is exists")
        }

        return SQLiteDatabase.openDatabase(dbFile.path, null, SQLiteDatabase.OPEN_READWRITE)
    }

    private fun copyDatabase(dbFile: File) {
        // mo database tu thu muc assets
        val openDB = context.assets.open(DB_NAME)
        val outputStream = FileOutputStream(dbFile)

        // chia nho file de thuc hien
        val buffer = ByteArray(1024)

        while(openDB.read(buffer) > 0){
            outputStream.write(buffer)
            Log.d("database","DB is writing")
        }
        outputStream.flush()
        outputStream.close()
        openDB.close()
        Log.d("db copy", "copy completed!")

    }
}