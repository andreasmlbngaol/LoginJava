package com.andreasmlbngaol.utils

import androidx.navigation.NavHostController

fun <T : Any> NavHostController.navigateAndPopUpTo(destination: T, startDestinationId: T) {
    this.navigate(destination) {
        launchSingleTop = true

        try {
            popUpTo(startDestinationId as Int) {
                inclusive = true
            }
        } catch (_: Exception) {
            popUpTo(startDestinationId) { inclusive = true }
        }
    }
}