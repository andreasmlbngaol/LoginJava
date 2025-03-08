package com.andreasmlbngaol.features.auth.route

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.andreasmlbngaol.features.auth.view.sign_in.SignInScreen
import com.andreasmlbngaol.route.SignInRoute
import com.andreasmlbngaol.route.SignUpRoute
import com.andreasmlbngaol.route.StudentHomeRoute
import com.andreasmlbngaol.route.TeacherHomeRoute
import com.andreasmlbngaol.utils.navigateAndPopUpTo

fun NavGraphBuilder.signInRoute(
    navController: NavHostController,
    onShowCloseAppDialog: () -> Unit
) {
    composable<SignInRoute> {
        SignInScreen(
            onCloseApp = onShowCloseAppDialog,
            onNavigateToSignUp = {
                navController.navigateAndPopUpTo(
                    SignUpRoute,
                    navController.currentDestination!!.id
                )
            },
            onNavigateToStudentHome = {
                navController.navigateAndPopUpTo(
                    StudentHomeRoute,
                    navController.currentDestination!!.id
                )
            },
            onNavigateToTeacherHome = {
                navController.navigateAndPopUpTo(
                    TeacherHomeRoute,
                    navController.currentDestination!!.id
                )
            }
        )
    }
}