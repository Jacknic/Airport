package com.jacknic.android.airport.ui.store.file

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.catalog.framework.annotations.Sample

/**
 * 使用文件存储信息
 *
 * @author Jacknic
 */

@Sample(
    name = "存储数据-文件",
    description = "通过读写文件存储数据",
    tags = ["store", "file", "data"]
)
@Composable
fun UsingFile() {
    val vm = viewModel<UserFileViewmodel>()
    val fileName = vm.fileName.collectAsState()
    val content = vm.content.collectAsState()
    Scaffold(bottomBar = {
        BottomAppBar {
            Button(modifier = Modifier.weight(1f), onClick = { vm.write() }) {
                Text("保存")
            }
            Button(modifier = Modifier.weight(1f), onClick = { vm.read() }) {
                Text("读取")
            }
            Button(modifier = Modifier.weight(1f), onClick = { vm.delete() }) {
                Text("删除")
            }
        }
    }) { padding ->
        Column(Modifier.padding(padding)) {
            Text("Note")
            TextField(fileName.value, onValueChange = {
                vm.setFileName(it)
            })
            TextField(content.value, onValueChange = {
                vm.setContent(it)
            })
        }
    }

}