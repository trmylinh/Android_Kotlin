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
    private val FILE_NAME = "listinfo.dat"

    // ghi tep
    fun writeData(item: ArrayList<DataTask>, context: Context) {
//        try{
//            val fileOutputStream: FileOutputStream =
//                context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)
//            val objectOutputStream = ObjectOutputStream(fileOutputStream)
//            objectOutputStream.writeObject(item)
//            objectOutputStream.close()
//            fileOutputStream.close()
//        }
//        catch (e: Exception) {
//            Log.e("error-write", e.message.toString())
//        }
        try {
            context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE).use { fileOutputStream ->
                ObjectOutputStream(fileOutputStream).use { objectOutputStream ->
                    objectOutputStream.writeObject(item)
                }
            }
        } catch (e: Exception) {
            Log.e("FileHelper", "Error writing data: ${e.message}")
        }

    }

    // doc tep
    fun readData(context: Context): ArrayList<DataTask> {
//        val itemList: ArrayList<String> = try {
//            val fileInputStream : FileInputStream = context.openFileInput(FILE_NAME)
//            val objectInputStream = ObjectInputStream(fileInputStream)
//            objectInputStream.readObject() as ArrayList<String>
//
//        } catch (e: FileNotFoundException){
//            ArrayList()
////            Log.e("FileHelper", "File not found: ${e.message}")
//        }
//        return itemList

        val itemList = ArrayList<DataTask>()
        try {
            context.openFileInput(FILE_NAME).use { fileInputStream ->
                ObjectInputStream(fileInputStream).use { objectInputStream ->
                    val data = objectInputStream.readObject()
                    if (data is ArrayList<*>) {
                        for (item in data) {
                            if (item is DataTask) {
                                itemList.add(item)
                            }
                        }
                    }
                }
            }
        } catch (e: FileNotFoundException) {
            Log.e("FileHelper", "File not found: ${e.message}")
        } catch (e: Exception) {
            Log.e("FileHelper", "Error reading data: ${e.message}")
        }
        return itemList

    }

}