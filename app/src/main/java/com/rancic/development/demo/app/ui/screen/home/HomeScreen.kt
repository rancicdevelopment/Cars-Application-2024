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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

import com.rancic.development.demo.app.model.Car
import com.rancic.development.demo.app.ui.components.ChipGroup
import com.rancic.development.demo.app.ui.components.CarItem
import com.rancic.development.demo.app.ui.components.model.Category
import com.rancic.development.demo.app.ui.components.model.CategoryType
import com.rancic.development.demo.app.viewmodel.CarViewModel


@Composable
fun HomeScreen(navController: (Car) -> Unit) {

    val carViewModel: CarViewModel = hiltViewModel()

    val carsList by carViewModel.carsFlow.collectAsState()
    carViewModel.carList()


    val categories = listOf(
        Category(0, CategoryType.ALL.name.lowercase()),
        Category(1, CategoryType.SUV.name.lowercase()),
        Category(2, CategoryType.HATCHBACK.name.lowercase())
    )

    var selectedItem by rememberSaveable {
        mutableStateOf(categories[0]) // initially, first item is selected
    }

    Box(
        Modifier.fillMaxSize()
    ) {
        Column {
            ChipGroup(
                categories,
                selectedItem
            ) {
                selectedItem = it
                carViewModel.carList()
            }
            LazyColumn(modifier = Modifier.padding(horizontal = 8.dp)) {
                carsList?.data?.let {
                    list ->

                    items(items = list) { cars ->
                        if (cars == null) return@items
                        CarItem(
                            car = cars,
                        ) {
                            navController(cars)
                        }
                    }
                }

            }
        }
    }
}