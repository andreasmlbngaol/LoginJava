package com.andreasmlbngaol.utils

import androidx.navigation.NavHostController

fun <T : Any> NavHostController.navigateAndClearBackStack(destination: T, startDestinationId: Int) {
    this.navigate(destination) {
        launchSingleTop = true

        popUpTo(startDestinationId) {
            inclusive = true
        }

    }
}