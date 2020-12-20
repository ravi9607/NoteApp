package com.example.makenotes

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note:Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("Select * from note order by id ASC")
    fun showAllNote(): LiveData<List<Note>>


}