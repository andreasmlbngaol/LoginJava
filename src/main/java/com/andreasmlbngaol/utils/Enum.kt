package com.andreasmlbngaol.utils

import java.util.*

fun String.enumToString(): String {
    return this
        .lowercase()
        .split("_").joinToString(" ") {
            it
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        }
}