package com.andreasmlbngaol.screens.home.student_home

import androidx.compose.runtime.mutableStateOf
import com.andreasmlbngaol.dto.Student
import com.andreasmlbngaol.service.AccountService

class StudentHomeViewModel {
    var user = mutableStateOf(AccountService.currentUser as? Student)
        private set

    fun logout(
        onLogout: () -> Unit
    ) {
        AccountService.logout()
        user.value = null // Update state agar UI bereaksi
        onLogout()
    }
}