package com.jacknic.android.airport

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.jacknic.android.airport.ui.store.file.InternalFileRepository
import com.jacknic.android.airport.ui.store.file.Note
import com.jacknic.android.airport.ui.store.file.NoteRepository
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * 内部文件存储测试
 */
@RunWith(AndroidJUnit4::class)
class InternalFileRepositoryTest {

    private lateinit var repo: NoteRepository
    private val note = Note("123", "木头人")


    @Before
    fun setup() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        repo = InternalFileRepository(appContext)
    }

    @After
    fun destroy() {
        repo.deleteNote(note)
    }

    @Test
    fun saveNoteTest() {
        repo.addNote(note)
        val noteSaved = repo.getNote(note.fileName)
        Assert.assertEquals(note.content, noteSaved.content)
    }

    @Test
    fun getNoteWhenNotSaved() {
        val note = repo.getNote(note.fileName)
        Assert.assertEquals("", note.content)
    }
}