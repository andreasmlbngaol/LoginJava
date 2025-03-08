package com.andreasmlbngaol.features.auth.view.register_student

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
fun RegisterStudentScreen(
    data: SignUpData,
    onBack: () -> Unit,
    onNavigateToSignIn: () -> Unit,
    viewModel: RegisterStudentViewModel = koinViewModel<RegisterStudentViewModel>()
) {
    val studentId by viewModel.studentId.collectAsState()
    val major by viewModel.major.collectAsState()
    val majorDropdownExpanded by viewModel.majorDropdownExpanded.collectAsState()
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
                        text = "Register Student",
                        style = MaterialTheme.typography.headlineSmall,
                    )

                    AnimatedVisibility(visible = errorMessageVisible) {
                        Text(
                            text = "Student ID is already registered!",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.error,
                        )
                    }

                    // StudentID
                    OutlinedTextField(
                        shape = MaterialTheme.shapes.medium,
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
                            shape = MaterialTheme.shapes.medium,
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
                            viewModel.registerStudent(data)
                        },
                        enabled = studentId.isNotEmpty()
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