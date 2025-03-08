package com.andreasmlbngaol.utils

fun String.isEmail(): Boolean {
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()
    return this.matches(emailRegex)}

fun Double.format(digits: Int = 2): String = "%.${digits}f".format(this)