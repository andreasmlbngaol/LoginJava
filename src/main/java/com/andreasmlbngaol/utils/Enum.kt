package com.andreasmlbngaol.utils

import java.util.*

fun String.enumToString(): String {
    return this
        .lowercase()
        .split("_").joinToString(" ") {
            it
                .replaceFirstChar { char ->
                    if (char.isLowerCase()) char.titlecase(Locale.getDefault()) else char.toString()
                }
        }
}