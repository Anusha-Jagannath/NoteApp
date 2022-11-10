package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.adapter.IClickListener
import com.example.notesapp.adapter.NoteAdapter
import com.example.notesapp.databinding.ActivityMainBinding
import com.example.notesapp.model.Note
import com.example.notesapp.viewmodel.NoteViewModel

class MainActivity : AppCompatActivity(), IClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: NoteViewModel
    private lateinit var noteList: ArrayList<Note>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        noteList = arrayListOf<Note>()
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        val adapter = NoteAdapter(noteList, this)
        binding.recyclerview.adapter = adapter
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[NoteViewModel::class.java]
        viewModel.allNotes.observe(this) { notes ->
            notes?.let {
                adapter.updateList(notes)
            }
        }

        binding.submitBtn.setOnClickListener {
            val text = binding.editText.text.toString()
            if (text.isNotEmpty()) {
                val note = Note(title = text)
                viewModel.insert(note)
                Toast.makeText(this, "note inserted successfully", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onClick(note: Note) {
        viewModel.delete(note)
    }
}