package com.pintu.kotlin_task.view.note

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pintu.kotlin_task.R
import kotlinx.android.synthetic.main.activity_main_note.*

class MainNote : AppCompatActivity(),INoteRVAdapter {

    lateinit var viewModel: NoteViewModel
    lateinit var backBtn :ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_note)
        backBtn = findViewById(R.id.backbtn)

        recycler_view.layoutManager = LinearLayoutManager(this)
        val adapter = NoteRVAdapter(this,this)
        recycler_view.adapter = adapter

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer {list->
            list?.let {
                adapter.updateList(it)
            }
        })
        backBtn.setOnClickListener(View.OnClickListener {
            super@MainNote.onBackPressed()
            finish()
        })

    }

    override fun onItemClicked(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this,"${note.text} Deleted",Toast.LENGTH_SHORT).show()
    }

    fun submitData(view: View) {
        val noteText = input.text.toString()
        if(noteText.isNotEmpty()){
            viewModel.insertNote(Note(noteText))
            Toast.makeText(this,"$noteText Inserted",Toast.LENGTH_SHORT).show()
        }
    }

}