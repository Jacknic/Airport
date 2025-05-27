package com.jacknic.android.airport.ui.compose.pager

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.google.android.catalog.framework.annotations.Sample

@Sample(
    name = "Compose 中的分页器 Pager",
    description = "如需向左或向右或向上或向下翻阅内容，您可以分别使用 HorizontalPager 和 VerticalPager 可组合项。",
    tags = ["compose", "layout", "pager"],
    documentation = "https://developer.android.google.cn/develop/ui/compose/layouts/pager"
)
@Composable
fun PagerScreen() {
    val pagerState = rememberPagerState(pageCount = { 10 })
    val pagerState2 = rememberPagerState(pageCount = { 10 })
    Column(verticalArrangement = Arrangement.SpaceBetween) {
        HorizontalPager(pagerState, modifier = Modifier.weight(0.5f)) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = "HorizontalPager: $it", textAlign = TextAlign.Center)
            }
        }
        VerticalPager(pagerState2, modifier = Modifier.weight(0.5f)) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = "VerticalPager: $it", textAlign = TextAlign.Center)
            }
        }
    }
}