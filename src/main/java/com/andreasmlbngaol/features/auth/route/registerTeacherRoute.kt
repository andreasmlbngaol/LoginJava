package com.andreasmlbngaol.features.auth.route

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.andreasmlbngaol.enums.Gender
import com.andreasmlbngaol.enums.Religion
import com.andreasmlbngaol.enums.Role
import com.andreasmlbngaol.features.auth.SignUpData
import com.andreasmlbngaol.features.auth.view.register_teacher.RegisterTeacherScreen
import com.andreasmlbngaol.route.RegisterTeacherRoute
import com.andreasmlbngaol.route.SignInRoute
import com.andreasmlbngaol.route.SignUpRoute
import com.andreasmlbngaol.utils.navigateAndPopUpTo

fun NavGraphBuilder.registerTeacherRoute(
    navController: NavHostController
) {
    composable<RegisterTeacherRoute> { backstackEntry ->
        val route: RegisterTeacherRoute = backstackEntry.toRoute()
        val data = SignUpData(
            email = route.email,
            password = route.password,
            firstName = route.firstName,
            lastName = route.lastName,
            gender = Gender.valueOf(route.gender),
            religion = Religion.valueOf(route.religion),
            role = Role.valueOf(route.role)
        )

        RegisterTeacherScreen(
            data,
            onBack = { navController.navigateUp() },
            onNavigateToSignIn = {
                navController.navigateAndPopUpTo(SignInRoute, SignUpRoute)
            }
        )
    }

}