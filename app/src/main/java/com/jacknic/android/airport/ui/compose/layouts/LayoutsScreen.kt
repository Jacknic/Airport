package com.jacknic.android.airport.ui.compose.layouts

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.catalog.framework.annotations.Sample
import com.jacknic.android.airport.R
import com.jacknic.android.airport.ui.theme.AirportTheme

@Sample(
    name = "Compose 中的基本布局",
    description = "实现一个更真实、更复杂的布局",
    tags = ["compose", "layout"],
    documentation = "https://developer.android.google.cn/codelabs/jetpack-compose-layouts"
)
@Composable
fun LayoutsScreen() {
    Scaffold { padding ->
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Spacer(Modifier.padding(top = padding.calculateTopPadding()))
            SearchBar(modifier = Modifier.padding(horizontal = 16.dp))
            HomeSection(R.string.ab1_inversions) {
                AlignYourBodyRow(alignYourBodyData = List(10) { R.drawable.ic_launcher_background to R.string.ab1_inversions })
            }
            HomeSection(R.string.ab1_inversions) {
                FavoriteCollectionGrid(
                    Modifier.height(500.dp),
                    List(50) { R.drawable.ic_launcher_background to R.string.ab1_inversions })
            }
            HomeSection(R.string.ab1_inversions) {
                FavoriteCollectionGrid(
                    Modifier.height(500.dp),
                    List(50) { R.drawable.ic_launcher_background to R.string.ab1_inversions })
            }
            Spacer(Modifier.padding(bottom = padding.calculateBottomPadding()))
        }
    }
}

@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(Icons.Default.Search, null)
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        placeholder = {
            Text(stringResource(R.string.placeholder_search))
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    )
}

@Composable
fun AlignYourBodyElement(
    @DrawableRes drawable: Int,
    @StringRes textRes: Int
) {
    Column(
        modifier = Modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painterResource(id = drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            stringResource(textRes),
            modifier = Modifier
                .paddingFromBaseline(top = 24.dp, bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun FavoriteCollectionCard(
    @DrawableRes drawableRes: Int,
    @StringRes textRes: Int,
    modifier: Modifier = Modifier
) {
    Surface(shape = MaterialTheme.shapes.medium, modifier = modifier.width(240.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(drawableRes), null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp)
            )
            Text(
                text = stringResource(textRes),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun FavoriteCollectionGrid(
    modifier: Modifier = Modifier,
    favoriteCollectionData: List<Pair<Int, Int>>
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(5), modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(items = favoriteCollectionData) {
            FavoriteCollectionCard(it.first, it.second, Modifier.height(80.dp))
        }
    }
}


@Composable
fun AlignYourBodyRow(modifier: Modifier = Modifier, alignYourBodyData: List<Pair<Int, Int>>) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(items = alignYourBodyData) {
            AlignYourBodyElement(it.first, it.second)
        }
    }
}

@Composable
fun HomeSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun AlignYourBodyElementPreview() {
    AirportTheme {
        AlignYourBodyElement(
            R.drawable.ic_launcher_background,
            R.string.ab1_inversions
        )
    }
}

@Preview
@Composable
fun SearchBarPreview() {
    AirportTheme {
        SearchBar()
    }
}

@Preview
@Composable
fun FavoriteCollectionCardPreview() {
    AirportTheme {
        FavoriteCollectionCard(
            textRes = R.string.ab1_inversions,
            drawableRes = R.drawable.ic_launcher_background
        )
    }
}

@Preview
@Composable
fun HomeSectionPreview() {
    AirportTheme {
        HomeSection(R.string.ab1_inversions) {
            AlignYourBodyElementPreview()
        }
    }
}

@Preview
@Composable
fun LayoutsScreenPreview() {
    LayoutsScreen()
}