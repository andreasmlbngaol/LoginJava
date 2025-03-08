package com.andreasmlbngaol.features.teacher.route

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.andreasmlbngaol.features.teacher.view.teacher_home.TeacherHomeScreen
import com.andreasmlbngaol.route.SignInRoute
import com.andreasmlbngaol.route.TeacherHomeRoute
import com.andreasmlbngaol.utils.navigateAndPopUpTo

fun NavGraphBuilder.teacherHomeRoute(
    navController: NavHostController,
    onShowCloseAppDialog: () -> Unit
) {
    composable<TeacherHomeRoute> {
        TeacherHomeScreen(
            onCloseApp = onShowCloseAppDialog,
            onNavigateToSignIn = {
                navController.navigateAndPopUpTo(SignInRoute, navController.currentDestination!!.id)
            }
        )
    }
}