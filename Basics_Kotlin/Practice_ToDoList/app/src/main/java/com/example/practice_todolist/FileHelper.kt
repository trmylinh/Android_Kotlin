package com.example.practice_todolist

import android.content.Context
import android.util.Log
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.util.Objects

class FileHelper {
    val FILE_NAME = "listinfo.dat"

    // ghi tep
    fun writeData(item: ArrayList<String>, context: Context) {
        val fileOutputStream: FileOutputStream =
            context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)
        val objectOutputStream = ObjectOutputStream(fileOutputStream)

        objectOutputStream.writeObject(item)
        objectOutputStream.close()
    }

    // doc tep
    fun readData(context: Context): ArrayList<String> {
//        val itemList: ArrayList<String> = try {
//            val fileInputStream : FileInputStream = context.openFileInput(FILE_NAME)
//            val objectInputStream = ObjectInputStream(fileInputStream)
//            objectInputStream.readObject() as ArrayList<String>
//
//        } catch (e: FileNotFoundException){
//            ArrayList()
//        }
//        return itemList
        val itemList: ArrayList<String> = ArrayList()
        try {
            val fileInputStream: FileInputStream = context.openFileInput(FILE_NAME)
            val objectInputStream = ObjectInputStream(fileInputStream)
            val data = objectInputStream.readObject()
            if (data is ArrayList<*>) {
                for (item in data) {
                    if (item is String) {
                        itemList.add(item)
                    }
                }
            }
            objectInputStream.close()
            fileInputStream.close()
        } catch (e: Exception) {
            Log.e("error", e.message.toString())
        }
        return itemList

    }

}