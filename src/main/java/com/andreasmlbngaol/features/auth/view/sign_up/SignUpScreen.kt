package com.andreasmlbngaol.features.auth.view.sign_up

import androidx.compose.animation.AnimatedVisibility
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
import com.andreasmlbngaol.features.auth.SignUpData
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    onCloseApp: () -> Unit,
    onNavigateToSignIn: () -> Unit,
    onNavigateToRegisterGeneral: (SignUpData) -> Unit,
    viewModel: SignUpViewModel = koinViewModel<SignUpViewModel>()
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
            val confirmPassword by viewModel.confirmPassword.collectAsState()
            val confirmPasswordErrorMessage by viewModel.confirmPasswordErrorMessage.collectAsState()
            val passwordVisible by viewModel.passwordVisible.collectAsState()
            val confirmPasswordVisible by viewModel.confirmPasswordVisible.collectAsState()
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
                        text = "Sign Up",
                        style = MaterialTheme.typography.headlineSmall,
                    )

                    Spacer(Modifier.height(16.dp))

                    AnimatedVisibility(visible = errorMessageVisible) {
                        Text(
                            text = "Email is already used!",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.error,
                        )
                    }

                    OutlinedTextField(
                        value = email,
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
                        shape = MaterialTheme.shapes.medium,
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
                            .padding(bottom = 16.dp)
                    )

                    OutlinedTextField(
                        isError = confirmPasswordErrorMessage != null,
                        supportingText = {
                            AnimatedVisibility(confirmPasswordErrorMessage != null) {
                                Text(
                                    text = confirmPasswordErrorMessage ?: "",
                                    color = MaterialTheme.colorScheme.error,
                                )
                            }
                        },
                        shape = MaterialTheme.shapes.medium,
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
                        enabled = email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty() && emailErrorMessage == null && confirmPasswordErrorMessage == null
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