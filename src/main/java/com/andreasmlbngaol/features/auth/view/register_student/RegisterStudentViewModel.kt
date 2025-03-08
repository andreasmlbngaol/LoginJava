package com.andreasmlbngaol.features.auth.view.register_student

import androidx.lifecycle.viewModelScope
import com.andreasmlbngaol.entity.StudentEntity
import com.andreasmlbngaol.enums.Major
import com.andreasmlbngaol.features.auth.RegisterViewModel
import com.andreasmlbngaol.repository.DatabaseRepository
import com.andreasmlbngaol.features.auth.SignUpData
import com.andreasmlbngaol.service.AccountService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterStudentViewModel(
    private val repository : DatabaseRepository
): RegisterViewModel() {
    private val _studentId = MutableStateFlow("")
    val studentId = _studentId.asStateFlow()

    val majors = Major.entries
    private val _major = MutableStateFlow(Major.OTHER)
    val major = _major.asStateFlow()

    private val _majorDropdownExpanded = MutableStateFlow(false)
    val majorDropdownExpanded = _majorDropdownExpanded.asStateFlow()

    fun setStudentId(value: String) {
        _studentId.value = value
    }

    fun setMajor(value: Major) {
        _major.value = value
    }

    fun toggleMajorDropdownExpanded() {
        _majorDropdownExpanded.value = !_majorDropdownExpanded.value
    }

    fun collapseMajorDropdown() {
        _majorDropdownExpanded.value = false
    }

    fun registerStudent(
        data: SignUpData,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            hideErrorMessage()
            if (_studentId.value.isNotEmpty() && repository.getStudentByStudentId(_studentId.value) == null) {
                AccountService.registerStudent(
                    repository,
                    StudentEntity(
                        data.email,
                        data.password,
                        data.firstName,
                        data.lastName,
                        data.gender,
                        data.religion,
                        _studentId.value,
                        _major.value,
                        0.0
                    )
                )
                showSuccessDialog()
            } else {
                showErrorMessage()
            }
        }
    }
}