package com.andreasmlbngaol.features.student.view.student_home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.andreasmlbngaol.dto.Student
import com.andreasmlbngaol.service.AccountService

class StudentHomeViewModel: ViewModel() {
    var user = mutableStateOf(AccountService.currentUser as Student)
        private set

    fun logout(
        onLogout: () -> Unit
    ) {
        AccountService.logout()
        onLogout()
    }
}