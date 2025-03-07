package com.andreasmlbngaol.screens.sign_up

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import com.andreasmlbngaol.repository.DatabaseRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    repo: DatabaseRepository,
    onCloseApp: () -> Unit,
    onNavigateToSignIn: () -> Unit,
    onNavigateToRegisterGeneral: (SignUpData) -> Unit
) {
    val viewModel = SignUpViewModel(repo)

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
            val password by viewModel.password.collectAsState()
            val confirmPassword by viewModel.confirmPassword.collectAsState()
            val passwordVisible by viewModel.passwordVisible.collectAsState()
            val confirmPasswordVisible by viewModel.confirmPasswordVisible.collectAsState()
//        val errorMessageVisible by viewModel.errorMessageVisible.collectAsState()

            val pinFocusRequester = remember { FocusRequester() }
            val focusManager = LocalFocusManager.current

            Card(
                shape = MaterialTheme.shapes.large
            ) {
                Column(
                    modifier = Modifier
                        .padding(40.dp)
                        .width(300.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Sign Up",
                        style = MaterialTheme.typography.headlineSmall,
                    )

                    Spacer(Modifier.height(8.dp))

//                AnimatedVisibility(visible = errorMessageVisible) {
//                    Text(
//                        text = "Email or Password incorrect!",
//                        style = MaterialTheme.typography.labelMedium,
//                        color = MaterialTheme.colorScheme.error,
//                    )
//                }

                    OutlinedTextField(
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
                            .fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = password,
                        onValueChange = viewModel::setPassword,
                        label = { Text("Password") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                pinFocusRequester.requestFocus()
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
                            .fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = confirmPassword,
                        onValueChange = viewModel::setConfirmPassword,
                        label = { Text("Confirm Password") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                focusManager.clearFocus()
                                viewModel.isEmailAvailable {
                                    onNavigateToRegisterGeneral(
                                        SignUpData(email, password)
                                    )
                                }
                            }
                        ),
                        singleLine = true,
                        visualTransformation = if (!confirmPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None,
                        trailingIcon = {
                            IconButton(
                                onClick = viewModel::toggleConfirmPasswordVisibility
                            ) {
                                Icon(
                                    imageVector = if (confirmPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                    contentDescription = "Show Confirm Password"
                                )
                            }
                        },
                        modifier = Modifier
                            .focusRequester(pinFocusRequester)
                            .fillMaxWidth()
                    )

                    Button(
                        onClick = {
                            focusManager.clearFocus()
                            viewModel.isEmailAvailable {
                                onNavigateToRegisterGeneral(
                                    SignUpData(email, password)
                                )
                            }
                        },
                        enabled = email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()
                    ) {
                        Text(text = "Continue")
                    }

                    TextButton(
                        onClick = onNavigateToSignIn
                    ) {
                        Text(text = "Already have an account? Sign up")
                    }

                }
            }
        }
    }
}