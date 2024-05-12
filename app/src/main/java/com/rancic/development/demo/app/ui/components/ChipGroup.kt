package com.rancic.development.demo.app.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rancic.development.demo.app.R
import com.rancic.development.demo.app.ui.components.model.Category

@Composable
fun ChipGroup(
    categories: List<Category>,
    selectedCategory: Category,
    onSelectedChanged: (Category) -> Unit = {},
) {
    Column(modifier = Modifier.padding(8.dp)) {
        LazyRow {
            item {
                IconButton(
                    onClick = { /* no-op */ }
                ) {
                    Icon(
                        imageVector = Icons.Default.List,
                        contentDescription = stringResource(R.string.label_filters)
                    )
                }
            }
            items(categories) {
                FilterChip(
                    label = {
                        Text(it.name.uppercase())
                    },
                    modifier = Modifier.padding(horizontal = 4.dp),
                    selected = selectedCategory == it,
                    onClick = {
                        onSelectedChanged(it)
                    },
                )
            }
        }
    }
}