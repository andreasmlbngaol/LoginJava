package com.andreasmlbngaol.features.auth.view.sign_in

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreasmlbngaol.enums.Role
import com.andreasmlbngaol.repository.DatabaseRepository
import com.andreasmlbngaol.service.AccountService
import com.andreasmlbngaol.utils.isEmail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignInViewModel(
    private val repo: DatabaseRepository
): ViewModel() {
    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _emailErrorMessage = MutableStateFlow<String?>(null)
    val emailError = _emailErrorMessage.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _passwordVisible = MutableStateFlow(false)
    val passwordVisible = _passwordVisible.asStateFlow()

    private val _errorMessageVisible = MutableStateFlow(false)
    val errorMessageVisible = _errorMessageVisible.asStateFlow()

    fun setEmail(email: String) {
        _email.value = email.trim()
        viewModelScope.launch(Dispatchers.IO) {
            if(!email.isEmail() && email.isNotBlank()) {
                _emailErrorMessage.value = "Insert valid email address"
            } else {
                _emailErrorMessage.value = null
            }
        }
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun togglePasswordVisibility() {
        _passwordVisible.value = !_passwordVisible.value
    }

    private fun showErrorMessage() {
        _errorMessageVisible.value = true
    }

    private fun hideErrorMessage() {
        _errorMessageVisible.value = false
    }

    fun login(
        email: String,
        password: String,
        onStudentLogin: () -> Unit,
        onTeacherLogin: () -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            hideErrorMessage()
            if(!AccountService.login(repo, email, password)) {
                showErrorMessage()
            } else {
                hideErrorMessage()
                val user = AccountService.currentUser!!
                withContext(Dispatchers.Main) {
                    if(user.role == Role.STUDENT) {
                        onStudentLogin()
                    } else {
                        onTeacherLogin()
                    }
                }
            }
        }
    }
}