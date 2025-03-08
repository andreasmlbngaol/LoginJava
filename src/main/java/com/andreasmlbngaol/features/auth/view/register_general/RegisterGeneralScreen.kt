package com.andreasmlbngaol.features.auth.view.register_general

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.andreasmlbngaol.features.auth.SignUpData
import com.andreasmlbngaol.utils.enumToString
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterGeneralScreen(
    data: SignUpData,
    onBack: () -> Unit,
    onNavigateToRegisterStudent: (SignUpData) -> Unit,
    onNavigateToRegisterTeacher: (SignUpData) -> Unit,
    viewModel: RegisterGeneralViewModel = koinViewModel<RegisterGeneralViewModel>()
) {
    val firstName by viewModel.firstName.collectAsState()
    val lastName by viewModel.lastName.collectAsState()
    val gender by viewModel.gender.collectAsState()
    val religion by viewModel.religion.collectAsState()
    val religionDropdownExpanded by viewModel.religionDropDownExpanded.collectAsState()
    val role by viewModel.role.collectAsState()

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
                        text = "Register",
                        style = MaterialTheme.typography.headlineSmall,
                    )

                    // Name
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            shape = MaterialTheme.shapes.medium,
                            value = firstName,
                            onValueChange = viewModel::setFirstName,
                            label = { Text("First Name") },
                            singleLine = true,
                            modifier = Modifier.weight(1f)
                        )

                        OutlinedTextField(
                            shape = MaterialTheme.shapes.medium,
                            value = lastName,
                            onValueChange = viewModel::setLastName,
                            label = { Text("Last Name") },
                            singleLine = true,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    // Gender
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        viewModel.genders.forEach { genderOption ->
                            Row(
                                modifier = Modifier
                                    .weight(1f)
                                    .selectable(
                                        selected = (genderOption == gender),
                                        onClick = { viewModel.setGender(genderOption) },
                                        role = Role.RadioButton
                                    ),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                RadioButton(
                                    selected = gender == genderOption,
                                    onClick = null
                                )
                                Spacer(Modifier.width(8.dp))
                                Text(
                                    text = genderOption
                                        .name
                                        .enumToString(),
                                )
                            }
                        }
                    }

                    // Religion
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            shape = MaterialTheme.shapes.medium,
                            modifier = Modifier.fillMaxWidth(),
                            value = religion.name.enumToString(),
                            onValueChange = {},
                            label = { Text("Religion") },
                            singleLine = true,
                            readOnly = true,
                            trailingIcon = {
                                IconButton(
                                    onClick = {
                                        viewModel.toggleReligionDropDownExpanded()
                                    }
                                ) {
                                    Icon(
                                        imageVector = if (!religionDropdownExpanded) Icons.Rounded.ArrowDropDown else Icons.Rounded.ArrowDropUp,
                                        contentDescription = if (!religionDropdownExpanded) "Expand" else "Collapse",
                                    )
                                }
                            }
                        )

                        DropdownMenu(
                            expanded = religionDropdownExpanded,
                            onDismissRequest = { viewModel.dismissReligionDropDown() }
                        ) {
                            viewModel.religions.forEach { religionOption ->
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            religionOption
                                                .name
                                                .enumToString(),
                                        )
                                    },
                                    onClick = {
                                        viewModel.setReligion(religionOption)
                                        viewModel.dismissReligionDropDown()
                                    }
                                )
                            }
                        }
                    }

                    // Role
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        viewModel.roles.forEach { roleOption ->
                            Row(
                                modifier = Modifier
                                    .weight(1f)
                                    .selectable(
                                        selected = (roleOption == role),
                                        onClick = { viewModel.setRole(roleOption) },
                                        role = Role.RadioButton
                                    ),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                RadioButton(
                                    selected = role == roleOption,
                                    onClick = null
                                )
                                Spacer(Modifier.width(8.dp))
                                Text(
                                    text = roleOption
                                        .name
                                        .enumToString(),
                                )
                            }
                        }
                    }

                    Button(
                        onClick = {
                            val newData = data.copy(
                                firstName = firstName,
                                lastName = lastName,
                                gender = gender,
                                religion = religion,
                                role = role
                            )

                            if(role == com.andreasmlbngaol.enums.Role.STUDENT) {
                                onNavigateToRegisterStudent(newData)
                            } else {
                                onNavigateToRegisterTeacher(newData)
                            }
                        },
                        enabled = firstName.isNotEmpty() && lastName.isNotEmpty(),
                    ) {
                        Text(text = "Continue")
                    }
                }
            }
        }
    }
}