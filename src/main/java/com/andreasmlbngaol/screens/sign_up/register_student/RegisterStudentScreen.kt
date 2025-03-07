package com.andreasmlbngaol.screens.sign_up.register_student

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material.icons.rounded.ArrowDropUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.andreasmlbngaol.repository.DatabaseRepository
import com.andreasmlbngaol.screens.sign_up.SignUpData
import com.andreasmlbngaol.utils.enumToString

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterStudentScreen(
    data: SignUpData,
    repo: DatabaseRepository,
    onBack: () -> Unit,
    onNavigateToSignIn: () -> Unit
) {
    val viewModel = RegisterStudentViewModel(repo)

    val studentId by viewModel.studentId.collectAsState()
    val major by viewModel.major.collectAsState()
    val majorDropdownExpanded by viewModel.majorDropdownExpanded.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("ONE LOGIN")
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBack
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
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
            Card(
                shape = MaterialTheme.shapes.large
            ) {
                Column(
                    modifier = Modifier
                        .padding(40.dp)
                        .width(300.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Register Student",
                        style = MaterialTheme.typography.headlineSmall,
                    )

                    // StudentID
                    OutlinedTextField(
                        value = studentId,
                        onValueChange = viewModel::setStudentId,
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        label = {
                            Text("Student ID")
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                        )
                    )

                    // Major
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = major.name.enumToString(),
                            onValueChange = {},
                            label = { Text("Major") },
                            singleLine = true,
                            readOnly = true,
                            trailingIcon = {
                                IconButton(
                                    onClick = {
                                        viewModel.toggleMajorDropdownExpanded()
                                    }
                                ) {
                                    Icon(
                                        imageVector = if (!majorDropdownExpanded) Icons.Rounded.ArrowDropDown else Icons.Rounded.ArrowDropUp,
                                        contentDescription = if (!majorDropdownExpanded) "Expand" else "Collapse",
                                    )
                                }
                            }
                        )

                        DropdownMenu(
                            expanded = majorDropdownExpanded,
                            onDismissRequest = { viewModel.collapseMajorDropdown() }
                        ) {
                            viewModel
                                .majors
                                .sortedBy { it.name }
                                .forEach { majorOption ->
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            majorOption
                                                .name
                                                .enumToString(),
                                        )
                                    },
                                    onClick = {
                                        viewModel.setMajor(majorOption)
                                        viewModel.collapseMajorDropdown()
                                    }
                                )
                            }
                        }
                    }

                    Button(
                        onClick = {
                            viewModel.registerStudent(data) {
                                onNavigateToSignIn()
                            }
                        },
                        enabled = studentId.isNotEmpty()
                    ) {
                        Text("Register")
                    }
                }
            }
        }
    }
}