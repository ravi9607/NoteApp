package com.example.makenotes.datalocal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteVM(application: Application) : AndroidViewModel(application) {
    private val repository: NoteRepository
    val allNotes: LiveData<List<Note>>

    init {
        val dao = NoteDB.getDatabase(application).getNoteDao()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes     //////////////////////////////////  allNotes
    }
    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO){
        repository.Delete(note)
    }
    fun insertNote(note: Note)=viewModelScope.launch (Dispatchers.IO){
        repository.Insert(note)
    }
}