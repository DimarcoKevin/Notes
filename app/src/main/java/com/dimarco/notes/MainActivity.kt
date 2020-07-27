package com.dimarco.notes

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SearchView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.ticket.view.*

class MainActivity : AppCompatActivity() {

    /**
     * creating the array list for all the notes
     */
    private var noteList = ArrayList<Note>()

    /**
     * on create starting method
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadQuery("%")
    }

    /**
     * creating a search query method
     * this method will search by title, or by all if nothing is chosen
     */
    private fun loadQuery(title: String) {
        // load notes from database
        var dbManager = DbManager(this)
        val columns = arrayOf("ID", "Title", "Content")
        val selectionArgs = arrayOf(title)
        val cursor = dbManager.query(columns, "Title LIKE ?", selectionArgs, "Title")
        noteList.clear()


        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex("ID"))
                val title = cursor.getString(cursor.getColumnIndex("Title"))
                val content = cursor.getString(cursor.getColumnIndex("Content"))
                noteList.add(Note(id, title, content))
            } while (cursor.moveToNext())
        }
        var notesAdapter = NotesAdapter(noteList)
        listView.adapter = notesAdapter

    }

    /**
     * creating search view and search manager
     * using a listener to catch text submit and text change in the text field
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)


        val sv = menu!!.findItem(R.id.searchBar).actionView as SearchView
        val sm = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        sv.setSearchableInfo(sm.getSearchableInfo(componentName))
        sv.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(applicationContext, query, Toast.LENGTH_LONG).show()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    /**
     * moving pages based on menu page chosen from status bar
     * the intent is used to show where you intend to move
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.addNote-> {
                var intent = Intent(this, AddNotes::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * this is the adapter that I used to connect to the notes
     */
    inner class NotesAdapter: BaseAdapter {
        private var noteListAdapter = ArrayList<Note>()
        constructor(noteListAdapter: ArrayList<Note>): super() {
            this.noteListAdapter = noteListAdapter
        }

        // creating the view with the layout inflater
        // using the note list adapter, each note is created with the title and content
        // this whole view is then returned
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var view = layoutInflater.inflate(R.layout.ticket, null)
            var note = noteListAdapter[position]

            view.tempTitle.text = note.title
            view.tempContent.text = note.content

            return view
        }

        override fun getItem(position: Int): Any {
            return noteListAdapter[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return noteListAdapter.size
        }
    }
}
