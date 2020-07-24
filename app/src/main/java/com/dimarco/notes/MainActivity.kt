package com.dimarco.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.ticket.view.*

class MainActivity : AppCompatActivity() {


    var noteList = ArrayList<Note>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteList.add(Note(1,"Bitcoin Note", "Bitcoin is a completely decentralized digital cryptocurrency. Unlike US dollars that you can hold in your hand (or in your bank account), there is no central authority or centralized payment system controlling Bitcoin. Instead, Bitcoin operates in a peer-to-peer network that allows anyone in the world to send and receive Bitcoin without any middleman (like a bank, central bank or payment processor)."))
        noteList.add(Note(2,"Ethereum Note", "Ethereum is a smart contract platform that enables developers to build decentralized applications (DApps) on its blockchain. Ether (ETH) is the native digital currency of the Ethereum platform."))
        noteList.add(Note(3,"Nano Note", "Launched in 2015, Nano describes itself as an open source, sustainable, and secure next-generation digital currency focused on removing perceived inefficiencies present in existing cryptocurrencies. Designed to solve peer to peer transfer of value, Nano aims to revolutionize the world economy through an ultrafast and fee-less network that is open and accessible to everyone."))

        var notesAdapter = NotesAdapter(noteList)
        listView.adapter = notesAdapter

    }

    inner class NotesAdapter: BaseAdapter {
        var noteListAdapter = ArrayList<Note>()
        constructor(noteListAdapter: ArrayList<Note>): super() {
            this.noteListAdapter = noteListAdapter
        }

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
