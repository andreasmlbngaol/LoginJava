package com.andreasmlbngaol.screens.sign_up.register_teacher

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
fun RegisterTeacherScreen(
    data: SignUpData,
    repo: DatabaseRepository,
    onBack: () -> Unit,
    onNavigateToSignIn: () -> Unit
) {
    val viewModel = RegisterTeacherViewModel(repo)

    val teacherId by viewModel.teacherId.collectAsState()
    val department by viewModel.department.collectAsState()
    val rank by viewModel.rank.collectAsState()
    val departmentDropdownExpanded by viewModel.departmentDropdownExpanded.collectAsState()
    val rankDropdownExpanded by viewModel.rankDropdownExpanded.collectAsState()

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
                        text = "Register Teacher",
                        style = MaterialTheme.typography.headlineSmall,
                    )

                    // Teacher ID
                    OutlinedTextField(
                        value = teacherId,
                        onValueChange = viewModel::setTeacherId,
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        label = {
                            Text("Teacher ID")
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                        )
                    )

                    // Department
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = department.name.enumToString(),
                            onValueChange = {},
                            label = { Text("Department") },
                            singleLine = true,
                            readOnly = true,
                            trailingIcon = {
                                IconButton(
                                    onClick = {
                                        viewModel.toggleDepartmentDropdownExpanded()
                                    }
                                ) {
                                    Icon(
                                        imageVector = if (!departmentDropdownExpanded) Icons.Rounded.ArrowDropDown else Icons.Rounded.ArrowDropUp,
                                        contentDescription = if (!departmentDropdownExpanded) "Expand" else "Collapse",
                                    )
                                }
                            }
                        )

                        DropdownMenu(
                            expanded = departmentDropdownExpanded,
                            onDismissRequest = { viewModel.collapseDepartmentDropdown() }
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
                                            viewModel.setDepartment(majorOption)
                                            viewModel.collapseDepartmentDropdown()
                                        }
                                    )
                                }
                        }
                    }

                    // Rank
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = rank.name.enumToString(),
                            onValueChange = {},
                            label = { Text("Academic Rank") },
                            singleLine = true,
                            readOnly = true,
                            trailingIcon = {
                                IconButton(
                                    onClick = {
                                        viewModel.toggleRankDropdownExpanded()
                                    }
                                ) {
                                    Icon(
                                        imageVector = if (!rankDropdownExpanded) Icons.Rounded.ArrowDropDown else Icons.Rounded.ArrowDropUp,
                                        contentDescription = if (!rankDropdownExpanded) "Expand" else "Collapse",
                                    )
                                }
                            }
                        )

                        DropdownMenu(
                            expanded = rankDropdownExpanded,
                            onDismissRequest = { viewModel.collapseRankDropdownExpanded() }
                        ) {
                            viewModel
                                .ranks
                                .forEach { rankOption ->
                                    DropdownMenuItem(
                                        text = {
                                            Text(
                                                rankOption
                                                    .name
                                                    .enumToString(),
                                            )
                                        },
                                        onClick = {
                                            viewModel.setRank(rankOption)
                                            viewModel.collapseRankDropdownExpanded()
                                        }
                                    )
                                }
                        }
                    }


                    Button(
                        onClick = {
                            viewModel.registerTeacher(data) {
                                onNavigateToSignIn()
                            }
                        },
                        enabled = teacherId.isNotEmpty()
                    ) {
                        Text("Register")
                    }
                }
            }
        }
    }
}