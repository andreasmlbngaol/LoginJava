package com.andreasmlbngaol.features.auth.route

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.andreasmlbngaol.enums.Gender
import com.andreasmlbngaol.enums.Religion
import com.andreasmlbngaol.enums.Role
import com.andreasmlbngaol.features.auth.SignUpData
import com.andreasmlbngaol.features.auth.view.register_student.RegisterStudentScreen
import com.andreasmlbngaol.route.RegisterStudentRoute
import com.andreasmlbngaol.route.SignInRoute
import com.andreasmlbngaol.route.SignUpRoute
import com.andreasmlbngaol.utils.navigateAndPopUpTo

fun NavGraphBuilder.registerStudentRoute(
    navController: NavHostController
) {
    composable<RegisterStudentRoute> { backstackEntry ->
        val route: RegisterStudentRoute = backstackEntry.toRoute()
        val data = SignUpData(
            email = route.email,
            password = route.password,
            firstName = route.firstName,
            lastName = route.lastName,
            gender = Gender.valueOf(route.gender),
            religion = Religion.valueOf(route.religion),
            role = Role.valueOf(route.role)
        )

        RegisterStudentScreen(
            data,
            onBack = { navController.navigateUp() },
            onNavigateToSignIn = {
                navController.navigateAndPopUpTo(SignInRoute, SignUpRoute)
            }
        )
    }
}