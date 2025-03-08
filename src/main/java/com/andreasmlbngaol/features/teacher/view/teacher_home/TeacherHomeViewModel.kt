package com.andreasmlbngaol.features.teacher.view.teacher_home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.andreasmlbngaol.dto.Teacher
import com.andreasmlbngaol.service.AccountService

class TeacherHomeViewModel: ViewModel() {
    var user = mutableStateOf(AccountService.currentUser as Teacher)
        private set

    fun logout(
        onLogout: () -> Unit
    ) {
        AccountService.logout()
        onLogout()
    }

}