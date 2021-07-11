package com.example.makenotes.datalocal

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert

class NoteRepository(private val noteDao: NoteDao) {
    val allNotes: LiveData<List<Note>> = noteDao.showAllNote()

    suspend fun Insert(note: Note){
        noteDao.insert(note)
    }
    suspend fun Delete(note: Note){
        noteDao.delete(note)
    }

}