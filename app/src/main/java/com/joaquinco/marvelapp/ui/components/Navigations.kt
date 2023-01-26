package com.joaquinco.marvelapp.ui.components

sealed class Screens(val route:String) {
    object Home: Screens(HOME_BASE_ROUTE)

    object Detail: Screens(DETAIL_ROUTE) {
        const val ARG_ID = "id"

        fun createRoute(id: String): String {
            return "$DETAIL_BASE_ROUTE/$id"
        }
    }

    companion object {
        private const val HOME_BASE_ROUTE = "home"
        private const val DETAIL_BASE_ROUTE = "detail"
        private const val DETAIL_ROUTE = "detail/{id}"
    }
}