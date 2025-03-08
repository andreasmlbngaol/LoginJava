package com.andreasmlbngaol.features.auth.route

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.andreasmlbngaol.features.auth.view.sign_up.SignUpScreen
import com.andreasmlbngaol.route.RegisterGeneralRoute
import com.andreasmlbngaol.route.SignInRoute
import com.andreasmlbngaol.route.SignUpRoute
import com.andreasmlbngaol.utils.navigateAndPopUpTo

fun NavGraphBuilder.signUpRoute(
    navController: NavHostController,
    onShowCloseAppDialog: () -> Unit
) {
    composable<SignUpRoute> {
        SignUpScreen(
            onCloseApp = onShowCloseAppDialog,
            onNavigateToSignIn = {
                navController.navigateAndPopUpTo(
                    SignInRoute,
                    navController.currentDestination!!.id
                )
            },
            onNavigateToRegisterGeneral = { data ->
                navController.navigate(RegisterGeneralRoute(data.email, data.password))
            }
        )
    }
}