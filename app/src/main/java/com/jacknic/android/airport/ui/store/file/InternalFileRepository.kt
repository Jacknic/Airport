package com.jacknic.android.airport.ui.store.file

import android.content.Context

/**
 * 内部存储
 */
class InternalFileRepository(private val context: Context) : NoteRepository {

    override fun addNote(note: Note) {
        context.openFileOutput(note.fileName, Context.MODE_PRIVATE).use {
            it.write(note.content.toByteArray())
        }
    }

    override fun getNote(fileName: String): Note {
        val file = context.getFileStreamPath(fileName)
        if (!file.exists()) {
            return Note(fileName, "")
        }
        val content = context.openFileInput(fileName).use {
            it.bufferedReader().readText()
        }
        return Note(fileName, content)
    }

    override fun deleteNote(note: Note): Boolean {
        return context.deleteFile(note.fileName)
    }
}