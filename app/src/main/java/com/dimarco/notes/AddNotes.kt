package com.dimarco.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class AddNotes : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)
    }

    fun btnAdd(view: View) {
        finish()
    }

    fun btnDelete(view: View) {
        finish()
    }
}
