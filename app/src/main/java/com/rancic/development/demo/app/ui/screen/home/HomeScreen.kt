package com.rancic.development.demo.app.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rancic.development.demo.app.common.Result
import com.rancic.development.demo.app.model.Car
import com.rancic.development.demo.app.ui.components.CarItem
import com.rancic.development.demo.app.ui.components.ChipGroup
import com.rancic.development.demo.app.ui.components.model.Category
import com.rancic.development.demo.app.ui.components.model.CategoryType
import com.rancic.development.demo.app.viewmodel.CarViewModel


@Composable
fun HomeScreen(
    navController: (Car) -> Unit,
    viewModel: CarViewModel = hiltViewModel()
) {
    val state = viewModel.carsFlow.collectAsState()

    var loading by remember { mutableStateOf(true) }
    var emptylist by remember { mutableStateOf(false) }


    when (state.value?.status) {
        Result.Status.LOADING -> {
            loading = true
        }

        Result.Status.SUCCESS -> {
            loading = false
            if (state.value?.data?.isEmpty() == true) {
                emptylist = true
            }
        }

        Result.Status.ERROR -> {
            loading = false
        }


        else -> {}
    }

    if (loading) {
        // ClassicCircularProgressBar()
    } else if (emptylist) {
        // NoFilesScreen()
    } else {
        state.value?.data?.let { list: List<Car> ->


            val categories = listOf(
                Category(0, CategoryType.ALL.name.lowercase()),
                Category(1, CategoryType.SUV.name.lowercase()),
                Category(2, CategoryType.HATCHBACK.name.lowercase())
            )

            var selectedItem by rememberSaveable { mutableStateOf(categories[0]) }

            Box(
                Modifier.fillMaxSize()
            ) {
                Column {
                    ChipGroup(categories, selectedItem) {
                        selectedItem = it
                    }
                    LazyColumn(modifier = Modifier.padding(horizontal = 8.dp)) {
                        items(items = list) { car ->
                            CarItem(car = car) {
                                navController(car)
                            }
                        }
                    }

                }
            }
        }


    }
}
