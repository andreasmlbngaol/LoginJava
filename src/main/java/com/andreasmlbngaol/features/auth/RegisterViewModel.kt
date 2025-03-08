package com.andreasmlbngaol.features.auth

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

open class RegisterViewModel : ViewModel() {
    private val _successDialogVisible = MutableStateFlow(false)
    val successDialogVisible = _successDialogVisible.asStateFlow()

    private val _errorMessageVisible = MutableStateFlow(false)
    val errorMessageVisible = _errorMessageVisible.asStateFlow()

    protected fun showSuccessDialog() {
        _successDialogVisible.value = true
    }

    fun dismissSuccessDialog() {
        _successDialogVisible.value = false
    }

    protected fun showErrorMessage() {
        _errorMessageVisible.value = true
    }

    fun hideErrorMessage() {
        _errorMessageVisible.value = false
    }
}