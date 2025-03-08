package com.andreasmlbngaol.features.auth.route

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.andreasmlbngaol.features.auth.SignUpData
import com.andreasmlbngaol.features.auth.view.register_general.RegisterGeneralScreen
import com.andreasmlbngaol.route.RegisterGeneralRoute
import com.andreasmlbngaol.route.RegisterStudentRoute
import com.andreasmlbngaol.route.RegisterTeacherRoute

fun NavGraphBuilder.registerGeneralRoute(navController: NavController) {
    composable<RegisterGeneralRoute> { backstackEntry ->
        val route: RegisterGeneralRoute = backstackEntry.toRoute()
        val data = SignUpData(route.email, route.password)

        RegisterGeneralScreen(
            data,
            onBack = { navController.navigateUp() },
            onNavigateToRegisterStudent = { newData ->
                navController.navigate(
                    RegisterStudentRoute(
                        newData.email,
                        newData.password,
                        newData.firstName!!,
                        newData.lastName!!,
                        newData.gender!!.name,
                        newData.religion!!.name,
                        newData.role!!.name)
                )
            },
            onNavigateToRegisterTeacher = { newData ->
                navController.navigate(
                    RegisterTeacherRoute(
                        newData.email,
                        newData.password,
                        newData.firstName!!,
                        newData.lastName!!,
                        newData.gender!!.name,
                        newData.religion!!.name,
                        newData.role!!.name)
                )
            }
        )
    }
}