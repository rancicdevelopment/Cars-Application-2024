package com.rancic.development.demo.app.ui.navigation

enum class State {
    HOME,
    DETAILS
}

sealed class NavigationItem(val route: String) {
    data object Home : NavigationItem(State.HOME.name)
    data object Details : NavigationItem(State.DETAILS.name)
}