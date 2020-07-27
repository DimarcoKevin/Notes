package com.dimarco.notes

import android.content.Context
import android.database.sqlite.SQLiteAbortException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbManager {

    val dbVersion = 1
    val dbName = "MyNotes"
    val dbTable = "Notes"
    val colID = "ID"
    val colTitle = "Title"
    val colContent = "Content"

    val sqlDB: SQLiteDatabase? = null
    val sqlCreateTable = "CREATE TABLE IF NOT EXIST $dbTable ($colID INTEGER PRIMARY KEY, $colTitle VARCHAR(255), $colContent VARCHAR(1024));"

    constructor()


    inner class DatabaseHelperNotes(context: Context) :
        SQLiteOpenHelper(context, dbName, null, dbVersion) {


        override fun onCreate(db: SQLiteDatabase?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }
}