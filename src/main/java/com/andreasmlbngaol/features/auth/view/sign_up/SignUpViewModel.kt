package com.andreasmlbngaol.features.auth.view.sign_up

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreasmlbngaol.repository.DatabaseRepository
import com.andreasmlbngaol.utils.isEmail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpViewModel(
    private val repository: DatabaseRepository
): ViewModel() {
    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _emailErrorMessage = MutableStateFlow<String?>(null)
    val emailError = _emailErrorMessage.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword = _confirmPassword.asStateFlow()

    private val _passwordVisible = MutableStateFlow(false)
    val passwordVisible = _passwordVisible.asStateFlow()

    private val _confirmPasswordVisible = MutableStateFlow(false)
    val confirmPasswordVisible = _confirmPasswordVisible.asStateFlow()

    private val _confirmPasswordErrorMessage = MutableStateFlow<String?>(null)
    val confirmPasswordErrorMessage = _confirmPasswordErrorMessage.asStateFlow()

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

    fun setConfirmPassword(confirmPassword: String) {
        _confirmPassword.value = confirmPassword
        viewModelScope.launch(Dispatchers.IO) {
            if(confirmPassword != _password.value && confirmPassword.isNotBlank()) {
                _confirmPasswordErrorMessage.value = "Passwords do not match"
            } else {
                _confirmPasswordErrorMessage.value = null
            }
        }
    }

    fun togglePasswordVisibility() {
        _passwordVisible.value = !_passwordVisible.value
    }

    fun toggleConfirmPasswordVisibility() {
        _confirmPasswordVisible.value = !_confirmPasswordVisible.value
    }

    private fun isPasswordMatch(): Boolean = _password.value == _confirmPassword.value

    fun isEmailAvailable(
        onAvailable: () -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            hideErrorMessage()
            if (isPasswordMatch() && repository.getUserByEmail(_email.value) == null) {
                withContext(Dispatchers.Main) {
                    onAvailable()
                }
            } else {
                delay(100L)
                showErrorMessage()
            }
        }
    }

    private fun showErrorMessage() {
        _errorMessageVisible.value = true
    }

    private fun hideErrorMessage() {
        _errorMessageVisible.value = false
    }

}