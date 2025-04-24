package com.jacknic.android.airport.ui.compose.appbars

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.google.android.catalog.framework.annotations.Sample

@Sample(
    name = "滚动顶部应用栏",
    description = "滚动顶部应用栏",
    tags = ["topBar", "bar"],
    documentation = ""
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmallTopAppBarScreen() {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Small Top App Bar")
                },
                scrollBehavior = scrollBehavior
            )
        },
    ) { innerPadding ->
        LazyColumn(
            Modifier
                .padding(innerPadding)
                .nestedScroll(scrollBehavior.nestedScrollConnection)
        ) {
            items(30) {
                ListItem(headlineContent = {
                    Text("ListItem $it")
                })
            }
        }
    }
}