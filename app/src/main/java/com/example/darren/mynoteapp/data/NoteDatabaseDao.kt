package com.example.darren.mynoteapp.data

import androidx.room.*
import com.example.darren.mynoteapp.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDatabaseDao {

    @Query(value = "SELECT * FROM notes_tbl")
    fun fetchAllNotes(): Flow<List<Note>>

    @Query(value = "SELECT * FROM notes_tbl WHERE note_id = :id")
    suspend fun fetchNoteById(id: String): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(note: Note)

    @Query(value = "DELETE FROM notes_tbl")
    suspend fun deleteAllNotes()

    @Delete
    suspend fun deleteNote(note: Note)
}