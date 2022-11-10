package com.example.notesapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notesapp.database.NoteDatabase
import com.example.notesapp.dao.NoteDao
import com.example.notesapp.model.Note
import com.example.notesapp.repository.NoteRepository
import kotlinx.coroutines.*


class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val noteDao: NoteDao
    private val noteRepository: NoteRepository

    init {
        noteDao = NoteDatabase.getDatabase(application).noteDao()
        noteRepository = NoteRepository(noteDao)
    }

    val allNotes: LiveData<List<Note>> = noteRepository.allNotes

    /**
     * Launching a new coroutine to delete the data in a non-blocking way
     */
    fun delete(note: Note) = viewModelScope
        .launch(Dispatchers.IO) {
        noteRepository.delete(note)
    }

    fun insert(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.insert(note)
        }
    }
}