package com.example.makenotes

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert

class NoteRepository(private val noteDao: NoteDao) {
    val getNote: LiveData<List<Note>> = noteDao.showAllNote()

    suspend fun Insert(note: Note){
        noteDao.insert(note)
    }
    suspend fun Delete(note: Note){
        noteDao.delete(note)
    }

}