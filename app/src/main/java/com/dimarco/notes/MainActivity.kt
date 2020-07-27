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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // adding dummy notes for testing
        noteList.add(Note(1,"Bitcoin Note", "Bitcoin is a completely decentralized digital cryptocurrency. Unlike US dollars that you can hold in your hand (or in your bank account), there is no central authority or centralized payment system controlling Bitcoin. Instead, Bitcoin operates in a peer-to-peer network that allows anyone in the world to send and receive Bitcoin without any middleman (like a bank, central bank or payment processor)."))
        noteList.add(Note(2,"Ethereum Note", "Ethereum is a smart contract platform that enables developers to build decentralized applications (DApps) on its blockchain. Ether (ETH) is the native digital currency of the Ethereum platform."))
        noteList.add(Note(3,"Nano Note", "Launched in 2015, Nano describes itself as an open source, sustainable, and secure next-generation digital currency focused on removing perceived inefficiencies present in existing cryptocurrencies. Designed to solve peer to peer transfer of value, Nano aims to revolutionize the world economy through an ultrafast and fee-less network that is open and accessible to everyone."))

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

            view.txtTitle.text = note.title
            view.txtContent.text = note.content

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
