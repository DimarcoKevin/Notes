package com.dimarco.notes

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_notes.*

class AddNotes : AppCompatActivity() {
    private val title = "Title"
    private val content = "Content"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)
    }

    /**
     * this will be called on click of the add notes submit button
     * it creates a copy of the database manager
     * then puts the values into a ContentValues object
     * then inserts them into the database
     */
    fun btnAdd(view: View) {
        var dbManager = DbManager(this)
        var values = ContentValues()
        values.put(title, txtTitle.text.toString())
        values.put(content, txtContent.text.toString())
        val id = dbManager.insert(values)

        if (id > 0) {
            Toast.makeText(this, "note has been added.", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "cannot add note.", Toast.LENGTH_LONG).show()
        }
    }

    fun btnDelete(view: View) {
        finish()
    }
}
