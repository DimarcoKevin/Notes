package com.dimarco.notes

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.widget.Toast

class DbManager {

    val dbVersion = 1
    val dbName = "MyNotes"
    val dbTable = "Notes"
    private val colID = "ID"
    private val colTitle = "Title"
    private val colContent = "Content"

    /**
     * creating database variable
     * creating sql table
     */
    var sqlDB: SQLiteDatabase? = null
    val sqlCreateTable = "CREATE TABLE IF NOT EXISTS $dbTable ($colID INTEGER PRIMARY KEY, $colTitle VARCHAR(255), $colContent VARCHAR(1024));"

    /**
     * constructor to create a readable/writeable database
     */
    constructor(context: Context) {
        val db = DatabaseHelperNotes(context)
        sqlDB = db.writableDatabase
    }

    /**
     * function used to insert new notes into the Notes table
     */
    fun insert(values: ContentValues): Long {
        return sqlDB!!.insert(dbTable, "", values)
    }

    /**
     * function is used to delete existing notes from the database
     */
    fun delete(selection: String, selectionArgs: Array<String>): Int {
        return sqlDB!!.delete(dbTable, selection, selectionArgs)
    }

    /**
     * function is used to edit existing notes
     */
    fun edit(values: ContentValues, selection: String, selectionArgs: Array<String>): Int {
        return sqlDB!!.update(dbTable, values, selection, selectionArgs)
    }

    /**
     * function used to find notes currently in the Notes table
     */
    fun query(columns: Array<String>, selection: String, selectionArgs: Array<String>, sortOrder: String): Cursor {
        val qb = SQLiteQueryBuilder()
        qb.tables = dbTable

        return qb.query(sqlDB, columns, selection, selectionArgs, null,null, sortOrder)
    }

    /**
     * notes database helper
     * used to create and upgrade tables for new versions of the application
     */
    inner class DatabaseHelperNotes(context: Context) :
        SQLiteOpenHelper(context, dbName, null, dbVersion) {

        private var context: Context? = null

        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL(sqlCreateTable)
            Toast.makeText(this.context, "database has been created.", Toast.LENGTH_LONG).show()
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("DROP TABLE IF EXISTS $dbTable")
        }

    }
}