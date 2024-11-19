package com.jacknic.android.airport.ui.store.file

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserFileViewmodel(val app: Application) : AndroidViewModel(app) {
    private val repo by lazy { InternalFileRepository(app) }
    private val _fileName = MutableStateFlow("")
    val fileName = _fileName.asStateFlow()

    private val _content = MutableStateFlow("")
    val content = _content.asStateFlow()

    fun setFileName(name: String) {
        _fileName.value = name
    }

    fun setContent(content: String) {
        _content.value = content
    }

    /**
     * 从文件中读取数据
     */
    fun read() {
        if (fileName.value.isBlank()) {
            Toast.makeText(app, "输入文件名", Toast.LENGTH_SHORT).show()
            return
        }
        val note = repo.getNote(fileName.value)
        _content.value = note.content
        _fileName.value = note.fileName
    }

    /**
     * 写入数据到文件
     */
    fun write() {
        if (fileName.value.isBlank() || content.value.isEmpty()) {
            Toast.makeText(app, "存储文件及内容不能为空", Toast.LENGTH_SHORT).show()
            return
        }
        repo.addNote(Note(fileName.value, content.value))
    }

    /**
     * 删除文件
     */
    fun delete() {
        repo.deleteNote(Note(fileName.value, ""))
        _fileName.value = ""
        _content.value = ""
    }

}