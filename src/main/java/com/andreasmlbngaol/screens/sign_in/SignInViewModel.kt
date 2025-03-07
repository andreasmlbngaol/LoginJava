package com.andreasmlbngaol.screens.sign_in

import com.andreasmlbngaol.enums.Role
import com.andreasmlbngaol.repository.DatabaseRepository
import com.andreasmlbngaol.service.AccountService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SignInViewModel(private val repo: DatabaseRepository) {
    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _passwordVisible = MutableStateFlow(false)
    val passwordVisible = _passwordVisible.asStateFlow()

    private val _errorMessageVisible = MutableStateFlow(false)
    val errorMessageVisible = _errorMessageVisible.asStateFlow()

    fun setEmail(email: String) {
        _email.value = email
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun togglePasswordVisibility() {
        _passwordVisible.value = !_passwordVisible.value
    }

    fun showErrorMessage() {
        _errorMessageVisible.value = true
    }

    fun hideErrorMessage() {
        _errorMessageVisible.value = false
    }

    fun login(
        email: String,
        password: String,
        onStudentLogin: () -> Unit,
        onTeacherLogin: () -> Unit
    ) {
        if(!AccountService.login(repo, email, password)) {
            showErrorMessage()
        } else {
            hideErrorMessage()
            val user = AccountService.currentUser!!
            if(user.role == Role.STUDENT) {
                onStudentLogin()
            } else {
                onTeacherLogin()
            }
        }
    }
}