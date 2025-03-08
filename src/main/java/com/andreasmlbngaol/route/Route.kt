package com.andreasmlbngaol.route

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.andreasmlbngaol.features.auth.route.*
import com.andreasmlbngaol.features.student.route.studentHomeRoute
import com.andreasmlbngaol.features.teacher.route.teacherHomeRoute
import kotlinx.serialization.Serializable

@Serializable
data object SignInRoute

@Serializable
data object SignUpRoute

@Serializable
data object StudentHomeRoute

@Serializable
data object TeacherHomeRoute

@Serializable
data class RegisterGeneralRoute(val email: String, val password: String)

@Serializable
data class RegisterStudentRoute(
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val religion: String,
    val role: String
)

@Serializable
data class RegisterTeacherRoute(
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val religion: String,
    val role: String
)

// Auth Route
fun NavGraphBuilder.authRoute(
    navController: NavHostController,
    onShowCloseDialog: () -> Unit
) {
    signInRoute(navController, onShowCloseDialog)
    signUpRoute(navController, onShowCloseDialog)
    registerGeneralRoute(navController)
    registerStudentRoute(navController)
    registerTeacherRoute(navController)
}

// Student Route
fun NavGraphBuilder.studentRoute(
    navController: NavHostController,
    onShowCloseDialog: () -> Unit
) {
    studentHomeRoute(navController, onShowCloseDialog)
}

// Teacher Route
fun NavGraphBuilder.teacherRoute(
    navController: NavHostController,
    onShowCloseDialog: () -> Unit
) {
    teacherHomeRoute(navController, onShowCloseDialog)
}