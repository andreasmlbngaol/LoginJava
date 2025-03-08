package com.andreasmlbngaol.features.auth.view.register_teacher

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material.icons.rounded.ArrowDropUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.andreasmlbngaol.features.auth.SignUpData
import com.andreasmlbngaol.utils.enumToString
import kotlinx.coroutines.delay
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterTeacherScreen(
    data: SignUpData,
    onBack: () -> Unit,
    onNavigateToSignIn: () -> Unit,
    viewModel: RegisterTeacherViewModel = koinViewModel<RegisterTeacherViewModel>()
) {

    val teacherId by viewModel.teacherId.collectAsState()
    val department by viewModel.department.collectAsState()
    val rank by viewModel.rank.collectAsState()
    val departmentDropdownExpanded by viewModel.departmentDropdownExpanded.collectAsState()
    val rankDropdownExpanded by viewModel.rankDropdownExpanded.collectAsState()
    val errorMessageVisible by viewModel.errorMessageVisible.collectAsState()

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

                    AnimatedVisibility(visible = errorMessageVisible) {
                        Text(
                            text = "Teacher is already registered!",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.error,
                        )
                    }

                    // Teacher ID
                    OutlinedTextField(
                        shape = MaterialTheme.shapes.medium,
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
                            shape = MaterialTheme.shapes.medium,
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
                            shape = MaterialTheme.shapes.medium,
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
                            viewModel.registerTeacher(data)
                        },
                        enabled = teacherId.isNotEmpty()
                    ) {
                        Text("Register")
                    }
                }
            }
        }
    }

    val successDialogVisible by viewModel.successDialogVisible.collectAsState()

    var countdown by remember { mutableIntStateOf(5) }
    LaunchedEffect(successDialogVisible) {
        if(successDialogVisible) {
            while (countdown > 0) {
                countdown -= 1
                delay(1000L)
            }
            viewModel.dismissSuccessDialog()
            onNavigateToSignIn()
        }
    }

    if(successDialogVisible) {
        AlertDialog(
            onDismissRequest = {
                viewModel.dismissSuccessDialog()
                onNavigateToSignIn()
            },
            title = { Text("Successfully Registered!") },
            confirmButton = { Text("Go") },
            text = {
                Text(text = "You will be redirected to login page in $countdown...")
            }
        )
    }

}