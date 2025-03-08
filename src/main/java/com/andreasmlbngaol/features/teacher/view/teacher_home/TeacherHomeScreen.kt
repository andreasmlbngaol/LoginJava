package com.andreasmlbngaol.features.teacher.view.teacher_home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Male
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material.icons.rounded.Face4
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.andreasmlbngaol.enums.Gender
import com.andreasmlbngaol.features.student.view.student_home.InfoData
import com.andreasmlbngaol.utils.enumToString
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeacherHomeScreen(
    onCloseApp: () -> Unit,
    onNavigateToSignIn: () -> Unit,
    viewModel: TeacherHomeViewModel = koinViewModel<TeacherHomeViewModel>()
) {
    val user = viewModel.user.value

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("ONE TEACHER")
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .width(400.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(100.dp)// Ukuran lingkaran
                            .clip(CircleShape) // Membuatnya jadi lingkaran
                            .border(3.dp, MaterialTheme.colorScheme.primary, CircleShape) // Border lingkaran
                    ) {
                        Icon(
                            imageVector = if(user.gender == Gender.MALE) Icons.Rounded.Face else Icons.Rounded.Face4, // Ganti dengan ikon lain
                            contentDescription = "Favorite",
                            modifier = Modifier.size(80.dp) // Ukuran ikon di dalam lingkaran
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = if(user.gender == Gender.MALE) Icons.Filled.Male else Icons.Filled.Female,
                            tint = if(user.gender == Gender.MALE) Color.Cyan else Color.Magenta,
                            contentDescription = "Gender"
                        )

                        Text(
                            text = user.fullName,
                            style = MaterialTheme.typography.titleLarge,
                            textDecoration = TextDecoration.Underline
                        )
                    }

                    Text(
                        text = user.teacherId,
                        style = MaterialTheme.typography.bodyMedium,
                        fontStyle = FontStyle.Italic
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    InfoData("Religion", user.religion.name.enumToString())
                    InfoData("Department", user.department.name.enumToString())
                    InfoData("Academic Rank", user.academicRank.name.enumToString())
                }
            }
        }
    }
}