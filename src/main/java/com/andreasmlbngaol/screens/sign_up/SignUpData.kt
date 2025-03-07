package com.andreasmlbngaol.screens.sign_up

import com.andreasmlbngaol.enums.Gender
import com.andreasmlbngaol.enums.Religion
import com.andreasmlbngaol.enums.Role

data class SignUpData(
    val email: String,
    val password: String,
    val role: Role? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val gender: Gender? = null,
    val religion: Religion? = null
)