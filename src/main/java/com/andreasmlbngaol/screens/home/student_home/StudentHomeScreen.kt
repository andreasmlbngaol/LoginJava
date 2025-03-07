package com.andreasmlbngaol.screens.home.student_home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentHomeScreen(
    onCloseApp: () -> Unit,
    onNavigateToSignIn: () -> Unit
) {
    val viewModel = StudentHomeViewModel()
    val user = viewModel.user

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("ONE STUDENT")
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
                },
                actions = {
                    Button(
                        onClick = {
                            viewModel.logout {
                                onNavigateToSignIn()
                            }
                        },
                        modifier = Modifier
                            .padding(end = 8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.Logout,
                                contentDescription = "Logout"
                            )

                            Text(
                                text = "Sign out",
                            )
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Text(
            text = user.toString(),
            modifier = Modifier.padding(innerPadding)
        )
    }
}