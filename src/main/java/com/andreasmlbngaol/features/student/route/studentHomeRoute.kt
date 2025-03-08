package com.andreasmlbngaol.features.student.route

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.andreasmlbngaol.features.student.view.student_home.StudentHomeScreen
import com.andreasmlbngaol.route.SignInRoute
import com.andreasmlbngaol.route.StudentHomeRoute
import com.andreasmlbngaol.utils.navigateAndPopUpTo

fun NavGraphBuilder.studentHomeRoute(
    navController: NavHostController,
    onoShowCloseAppDialog: () -> Unit
) {
    composable<StudentHomeRoute> {
        StudentHomeScreen(
            onCloseApp = onoShowCloseAppDialog,
            onNavigateToSignIn = {
                navController.navigateAndPopUpTo(SignInRoute, navController.currentDestination!!.id)
            }
        )
    }
}