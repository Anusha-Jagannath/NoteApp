package com.example.notesapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
class Note( @ColumnInfo(name = "title") val title: String) {

    @PrimaryKey(autoGenerate = true) var id: Int = 0
}