package com.andreasmlbngaol.screens.home.teacher_home

import androidx.compose.runtime.mutableStateOf
import com.andreasmlbngaol.dto.Teacher
import com.andreasmlbngaol.service.AccountService

class TeacherHomeViewModel {
    var user = mutableStateOf(AccountService.currentUser as? Teacher)
        private set

    fun logout(
        onLogout: () -> Unit
    ) {
        AccountService.logout()
        user.value = null // Update state agar UI bereaksi
        onLogout()
    }

}