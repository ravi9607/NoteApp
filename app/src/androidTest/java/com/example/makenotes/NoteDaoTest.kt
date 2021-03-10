package com.example.makenotes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class NoteDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database:NoteDB
    private lateinit var dao:NoteDao

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            NoteDB::class.java
        ).allowMainThreadQueries().build()

        dao = database.getNoteDao()

    }

    @After
    fun teardown(){
        database.close()
    }

    @Test
    fun insertNotes()= runBlockingTest {
        val note = Note("Hello for Test")
        dao.insert(note)

        val allNotes = dao.showAllNote().getOrAwaitValue()
        assertThat(allNotes).contains(note)
    }

    @Test
    fun deleteTest() = runBlockingTest {
        val note = Note("Hello for Test")
        dao.insert(note)
        dao.delete(note)

        val allNotes = dao.showAllNote().getOrAwaitValue()
        assertThat(allNotes).contains(note)

    }
}