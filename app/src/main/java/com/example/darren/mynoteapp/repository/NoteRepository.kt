package com.example.darren.mynoteapp.repository

import com.example.darren.mynoteapp.data.NoteDatabaseDao
import com.example.darren.mynoteapp.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDatabaseDao: NoteDatabaseDao) {
    suspend fun addNote(note: Note) = noteDatabaseDao.insertNote(note)
    suspend fun deleteNote(note: Note) = noteDatabaseDao.deleteNote(note)
    suspend fun updateNote(note: Note) = noteDatabaseDao.updateNote(note)
    suspend fun deleteAllNotes() = noteDatabaseDao.deleteAllNotes()
    suspend fun fetchNoteById(id: String): Note = noteDatabaseDao.fetchNoteById(id)
    fun fetchAllNotes(): Flow<List<Note>> = noteDatabaseDao.fetchAllNotes().flowOn(Dispatchers.IO).conflate()
}