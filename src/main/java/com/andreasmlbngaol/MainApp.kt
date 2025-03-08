package com.andreasmlbngaol

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.*
import androidx.navigation.compose.rememberNavController
import com.andreasmlbngaol.db.Database
import com.andreasmlbngaol.di.initKoin
import com.andreasmlbngaol.theme.LoginJavaTheme
import org.koin.compose.KoinContext

fun ApplicationScope.closeApplication() {
    Database.shutdown()
    exitApplication()
}

fun main() {
    initKoin()
    application {
        LaunchedEffect(Unit) {
            Database.init()
        }

        val windowState = rememberWindowState(placement = WindowPlacement.Fullscreen)
        Window(
            onCloseRequest = ::closeApplication,
            state = windowState,
            undecorated = true,
            resizable = false
        ) {
            App(::closeApplication)
        }
    }
}

@Composable
fun App(
    onExitApplication: () -> Unit
) {
    val navController = rememberNavController()
    LoginJavaTheme {
        KoinContext {
            Surface(
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier.fillMaxSize()
            ) {
                MainNavHost(navController, onExitApplication)
            }
        }
    }
}