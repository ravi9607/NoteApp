package com.example.makenotes

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.makenotes.datalocal.Note
import com.example.makenotes.datalocal.NoteAdapter
import com.example.makenotes.datalocal.NoteVM
import com.example.makenotes.datalocal.iNoteAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), iNoteAdapter {
    lateinit var viewModel: NoteVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        supportActionBar?.title = "Notes"

        //recyclerView.scrollState
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter= NoteAdapter(this,this)
        recyclerView.adapter=adapter

        viewModel= ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
            NoteVM::class.java)
        viewModel.allNotes.observe(this, Observer {list ->
            list?.let{
                    adapter.updateList(it)
            }
        })

    }

    override fun onItemClick(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this,"Delete Note",Toast.LENGTH_LONG).show()
    }

    fun Submit(view: View) {
        val noteText = enterText.text.toString()
        if(noteText.isNotEmpty()){
            viewModel.insertNote(Note(noteText))
            Toast.makeText(this,"Insert Note",Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this,"please enter Note",Toast.LENGTH_LONG).show()
        }

    }
}