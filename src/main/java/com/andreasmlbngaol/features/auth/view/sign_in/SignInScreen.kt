package com.andreasmlbngaol.features.auth.view.sign_in

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    onCloseApp: () -> Unit,
    onNavigateToSignUp: () -> Unit,
    onNavigateToStudentHome: () -> Unit,
    onNavigateToTeacherHome: () -> Unit,
    viewModel: SignInViewModel = koinViewModel<SignInViewModel>()
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("ONE LOGIN")
                },
                navigationIcon = {
                    IconButton(
                        onClick = onCloseApp
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Close App"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            val email by viewModel.email.collectAsState()
            val emailErrorMessage by viewModel.emailError.collectAsState()
            val password by viewModel.password.collectAsState()
            val passwordVisible by viewModel.passwordVisible.collectAsState()
            val errorMessageVisible by viewModel.errorMessageVisible.collectAsState()

            val pinFocusRequester = remember { FocusRequester() }
            val focusManager = LocalFocusManager.current

            Card(
                shape = MaterialTheme.shapes.large
            ) {
                Column(
                    modifier = Modifier
                        .padding(40.dp)
                        .width(300.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Sign In",
                        style = MaterialTheme.typography.headlineSmall,
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    AnimatedVisibility(visible = errorMessageVisible) {
                        Text(
                            text = "Email or Password incorrect!",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.error,
                        )
                    }

                    OutlinedTextField(
                        isError = emailErrorMessage != null,
                        supportingText = {
                            AnimatedVisibility(emailErrorMessage != null) {
                                Text(
                                    text = emailErrorMessage ?: "",
                                    color = MaterialTheme.colorScheme.error,
                                )
                            }
                        },
                        shape = MaterialTheme.shapes.medium,
                        value = email,
                        onValueChange = viewModel::setEmail,
                        label = { Text("Email") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                pinFocusRequester.requestFocus()
                            }
                        ),
                        singleLine = true,
                        modifier = Modifier
                            .width(300.dp)
                    )
                    OutlinedTextField(
                        shape = MaterialTheme.shapes.medium,
                        value = password,
                        onValueChange = viewModel::setPassword,
                        label = { Text("Password") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                focusManager.clearFocus()
                                viewModel.login(
                                    email,
                                    password,
                                    onNavigateToStudentHome,
                                    onNavigateToTeacherHome
                                )
                            }
                        ),
                        singleLine = true,
                        visualTransformation = if (!passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
                        trailingIcon = {
                            IconButton(
                                onClick = viewModel::togglePasswordVisibility
                            ) {
                                Icon(
                                    imageVector = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                    contentDescription = "Show Password"
                                )
                            }
                        },
                        modifier = Modifier
                            .focusRequester(pinFocusRequester)
                            .width(300.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            focusManager.clearFocus()
                            viewModel.login(
                                email,
                                password,
                                onNavigateToStudentHome,
                                onNavigateToTeacherHome
                            )
                        },
                        enabled = emailErrorMessage == null && email.isNotEmpty() && password.isNotEmpty()
                    ) {
                        Text(text = "Login")
                    }

                    TextButton(
                        onClick = onNavigateToSignUp
                    ) {
                        Text(text = "Don't have an account? Sign up")
                    }

                }
            }
        }
    }
}