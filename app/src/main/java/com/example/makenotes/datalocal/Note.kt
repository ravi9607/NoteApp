package com.example.makenotes.datalocal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Note")

class Note(
    @ColumnInfo(name="text") val text: String
    ) {

    @PrimaryKey(autoGenerate = true) var id: Int = 0

}