package com.andreasmlbngaol.screens.sign_up.register_general

import com.andreasmlbngaol.enums.Gender
import com.andreasmlbngaol.enums.Religion
import com.andreasmlbngaol.enums.Role
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RegisterGeneralViewModel {
    private val _firstName = MutableStateFlow("")
    val firstName = _firstName.asStateFlow()

    private val _lastName = MutableStateFlow("")
    val lastName = _lastName.asStateFlow()

    val genders = Gender.entries.toList()
    private val _gender = MutableStateFlow(genders.first())
    val gender = _gender.asStateFlow()

    val religions = Religion.entries.toList()
    private val _religion = MutableStateFlow(Religion.UNDEFINED)
    val religion = _religion.asStateFlow()

    private val _religionDropDownExpanded = MutableStateFlow(false)
    val religionDropDownExpanded = _religionDropDownExpanded.asStateFlow()

    val roles = Role.entries.toList()
    private val _role = MutableStateFlow(roles.first())
    val role = _role.asStateFlow()

    fun setFirstName(name: String) {
        _firstName.value = name
    }

    fun setLastName(name: String) {
        _lastName.value = name
    }

    fun setGender(gender: Gender) {
        _gender.value = gender
    }

    fun setReligion(religion: Religion) {
        _religion.value = religion
    }

    fun toggleReligionDropDownExpanded() {
        _religionDropDownExpanded.value = !_religionDropDownExpanded.value
    }

    fun dismissReligionDropDown() {
        _religionDropDownExpanded.value = false
    }

    fun setRole(role: Role) {
        _role.value = role
    }
}