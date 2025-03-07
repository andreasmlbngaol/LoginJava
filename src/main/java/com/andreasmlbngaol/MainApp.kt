package com.andreasmlbngaol

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.andreasmlbngaol.db.Database
import com.andreasmlbngaol.enums.Gender
import com.andreasmlbngaol.enums.Religion
import com.andreasmlbngaol.enums.Role
import com.andreasmlbngaol.repository.DatabaseRepository
import com.andreasmlbngaol.screens.home.student_home.StudentHomeScreen
import com.andreasmlbngaol.screens.home.teacher_home.TeacherHomeScreen
import com.andreasmlbngaol.screens.sign_in.SignInScreen
import com.andreasmlbngaol.screens.sign_up.SignUpData
import com.andreasmlbngaol.screens.sign_up.SignUpScreen
import com.andreasmlbngaol.screens.sign_up.register_general.RegisterGeneralScreen
import com.andreasmlbngaol.screens.sign_up.register_student.RegisterStudentScreen
import com.andreasmlbngaol.screens.sign_up.register_teacher.RegisterTeacherScreen
import com.andreasmlbngaol.theme.LoginJavaTheme
import com.andreasmlbngaol.utils.navigateAndClearBackStack
import kotlinx.serialization.Serializable

fun main() = application {
    val windowState = rememberWindowState(placement = WindowPlacement.Fullscreen)

    LaunchedEffect(Unit) {
        Database.init()
    }

    Window(
        onCloseRequest = ::exitApplication,
        state = windowState,
        undecorated = true,
        resizable = false
    ) {
        App(::exitApplication)
    }
}

@Composable
fun App(
    onExitApplication: () -> Unit
) {
    val navController = rememberNavController()
    LoginJavaTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            MainNavHost(navController, onExitApplication)
        }
    }
}

@Serializable data object SignInRoute
@Serializable data object SignUpRoute
@Serializable data object StudentHomeRoute
@Serializable data object TeacherHomeRoute
@Serializable data class RegisterGeneralRoute(val email: String, val password: String)
@Serializable data class RegisterStudentRoute(
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val religion: String,
    val role: String
)
@Serializable data class RegisterTeacherRoute(
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val religion: String,
    val role: String
)

@Composable
fun MainNavHost(
    navController: NavHostController,
    onExitApplication: () -> Unit
) {
    val repo = DatabaseRepository()
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
        composable<SignInRoute> {
            SignInScreen(
                repo,
                onCloseApp = { showCloseAppDialog() },
                onNavigateToSignUp = {
                    navController.navigateAndClearBackStack(
                        SignUpRoute,
                        navController.graph.startDestinationId
                    )
                },
                onNavigateToStudentHome = {
                    navController.navigateAndClearBackStack(
                        StudentHomeRoute,
                        navController.graph.startDestinationId
                    )
                },
                onNavigateToTeacherHome = {
                    navController.navigateAndClearBackStack(
                        TeacherHomeRoute,
                        navController.graph.startDestinationId
                    )
                }
            )
        }

        composable<SignUpRoute> {
            SignUpScreen(
                repo,
                onCloseApp = {
                    showCloseAppDialog()
                },
                onNavigateToSignIn = {
                    navController.navigateAndClearBackStack(
                        SignInRoute,
                        navController.graph.startDestinationId
                    )
                },
                onNavigateToRegisterGeneral = { data ->
                    navController.navigate(RegisterGeneralRoute(data.email, data.password))
                }
            )
        }

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
                repo,
                onBack = { navController.navigateUp() },
                onNavigateToSignIn = {
                    navController.navigateAndClearBackStack(SignInRoute, navController.graph.startDestinationId)
                }
            )
        }

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
                repo,
                onBack = { navController.navigateUp() },
                onNavigateToSignIn = {
                    navController.navigateAndClearBackStack(SignInRoute, navController.graph.startDestinationId)
                }
            )
        }


        composable<StudentHomeRoute> {
            StudentHomeScreen(
                onCloseApp = {
                    showCloseAppDialog()
                },
                onNavigateToSignIn = {
                    navController.navigateAndClearBackStack(SignInRoute, navController.graph.startDestinationId)
                }
            )
        }

        composable<TeacherHomeRoute> {
            TeacherHomeScreen(
                onCloseApp = {
                    showCloseAppDialog()
                },
                onNavigateToSignIn = {
                    navController.navigateAndClearBackStack(SignInRoute, navController.graph.startDestinationId)
                }
            )
        }
    }

    if(showCloseAppDialog) {
        AlertDialog(
            onDismissRequest = { dismissCloseAppDialog()},
            title = { Text("Close App?") },
            text = { Text("Are you sure want to leave the app? 🥺🥺🥺") },
            confirmButton = {
                Button(
                    onClick = {
                        onExitApplication()
                    },
                    colors = ButtonDefaults.buttonColors().copy(
                        containerColor = MaterialTheme.colorScheme.errorContainer,
                        contentColor = MaterialTheme.colorScheme.onErrorContainer,
                    )
                ) {
                    Text("Close App")
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = {
                        dismissCloseAppDialog()
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }

}