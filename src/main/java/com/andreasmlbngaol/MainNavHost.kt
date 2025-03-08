package com.andreasmlbngaol

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.andreasmlbngaol.route.SignInRoute
import com.andreasmlbngaol.route.authRoute
import com.andreasmlbngaol.route.studentRoute
import com.andreasmlbngaol.route.teacherRoute

@Composable
fun MainNavHost(
    navController: NavHostController,
    onExitApplication: () -> Unit
) {
    var showCloseAppDialog by remember { mutableStateOf(false) }

    fun showCloseAppDialog() {
        showCloseAppDialog = true
    }

    fun dismissCloseAppDialog() {
        showCloseAppDialog = false
    }

    NavHost(
        navController = navController,
        startDestination = SignInRoute
    ) {
        authRoute(navController, ::showCloseAppDialog)
        studentRoute(navController, ::showCloseAppDialog)
        teacherRoute(navController, ::showCloseAppDialog)
    }

    if(showCloseAppDialog) {
        CloseAppDialog(
            onDismissCloseAppDialog = ::dismissCloseAppDialog,
            onExitApplication = onExitApplication
        )
    }
}


@Composable
fun CloseAppDialog(
    onDismissCloseAppDialog: () -> Unit,
    onExitApplication: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissCloseAppDialog,
        title = { Text("Close App?") },
        text = { Text("Are you sure want to leave the app? 🥺🥺🥺") },
        confirmButton = {
            Button(
                onClick = onExitApplication,
                colors = ButtonDefaults.buttonColors().copy(
                    containerColor = MaterialTheme.colorScheme.error,
                    contentColor = MaterialTheme.colorScheme.onError,
                )
            ) {
                Text("Close App")
            }
        },
        dismissButton = {
            OutlinedButton(
                onClick = onDismissCloseAppDialog
            ) {
                Text("Cancel")
            }
        }
    )
}