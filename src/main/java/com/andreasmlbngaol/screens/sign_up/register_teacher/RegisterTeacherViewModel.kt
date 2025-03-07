package com.andreasmlbngaol.screens.sign_up.register_teacher

import com.andreasmlbngaol.entity.TeacherEntity
import com.andreasmlbngaol.enums.AcademicRank
import com.andreasmlbngaol.enums.Major
import com.andreasmlbngaol.repository.DatabaseRepository
import com.andreasmlbngaol.screens.sign_up.SignUpData
import com.andreasmlbngaol.service.AccountService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RegisterTeacherViewModel(
    private val repo: DatabaseRepository
) {
    private val _teacherId = MutableStateFlow("")
    val teacherId = _teacherId.asStateFlow()

    val majors = Major.entries
    private val _department = MutableStateFlow(Major.OTHER)
    val department = _department.asStateFlow()

    val ranks = AcademicRank.entries
    private val _rank = MutableStateFlow(AcademicRank.OTHER)
    val rank = _rank.asStateFlow()

    private val _departmentDropdownExpanded = MutableStateFlow(false)
    val departmentDropdownExpanded = _departmentDropdownExpanded.asStateFlow()

    private val _rankDropdownExpanded = MutableStateFlow(false)
    val rankDropdownExpanded = _rankDropdownExpanded.asStateFlow()

    fun setTeacherId(teacherId: String) {
        _teacherId.value = teacherId
    }

    fun setDepartment(department: Major) {
        _department.value = department
    }

    fun setRank(rank: AcademicRank) {
        _rank.value = rank
    }

    fun toggleDepartmentDropdownExpanded() {
        _departmentDropdownExpanded.value = !_departmentDropdownExpanded.value
    }

    fun collapseDepartmentDropdown() {
        _departmentDropdownExpanded.value = false
    }

    fun toggleRankDropdownExpanded() {
        _rankDropdownExpanded.value = !_rankDropdownExpanded.value
    }

    fun collapseRankDropdownExpanded() {
        _rankDropdownExpanded.value = false
    }

    fun registerTeacher(
        data: SignUpData,
        onSuccess: () -> Unit
    ) {
        if(_teacherId.value.isNotEmpty() && repo.getTeacherByTeacherId(_teacherId.value) == null) {
            AccountService.registerTeacher(
                repo,
                TeacherEntity(
                    data.email,
                    data.password,
                    data.firstName,
                    data.lastName,
                    data.gender,
                    data.religion,
                    _teacherId.value,
                    _department.value,
                    _rank.value
                )
            )
            onSuccess()
        } else {

        }
    }
}