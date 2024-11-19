package com.jacknic.android.airport.ui.store.file

interface NoteRepository {

    /**
     * 添加并保存笔记
     */
    fun addNote(note: Note)

    /**
     * 获取已存储的笔记
     */
    fun getNote(fileName: String): Note

    /**
     * 删除已保存笔记
     */
    fun deleteNote(note: Note): Boolean
}