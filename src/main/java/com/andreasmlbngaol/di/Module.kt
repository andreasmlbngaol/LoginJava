package com.andreasmlbngaol.di

import com.andreasmlbngaol.features.auth.view.register_general.RegisterGeneralViewModel
import com.andreasmlbngaol.features.auth.view.register_student.RegisterStudentViewModel
import com.andreasmlbngaol.features.auth.view.register_teacher.RegisterTeacherViewModel
import com.andreasmlbngaol.features.auth.view.sign_in.SignInViewModel
import com.andreasmlbngaol.features.auth.view.sign_up.SignUpViewModel
import com.andreasmlbngaol.features.student.view.student_home.StudentHomeViewModel
import com.andreasmlbngaol.features.teacher.view.teacher_home.TeacherHomeViewModel
import com.andreasmlbngaol.repository.DatabaseRepository
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val modules = module {
    single { DatabaseRepository() }

    // Auth
    viewModelOf(::SignInViewModel)
    viewModelOf(::SignUpViewModel)
    viewModelOf(::RegisterGeneralViewModel)
    viewModelOf(::RegisterStudentViewModel)
    viewModelOf(::RegisterTeacherViewModel)

    // Student
    viewModelOf(::StudentHomeViewModel)

    // Teacher
    viewModelOf(::TeacherHomeViewModel )
}