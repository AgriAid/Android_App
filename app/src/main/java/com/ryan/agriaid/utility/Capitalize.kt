package com.ryan.agriaid.utility

import java.util.Locale

object Capitalize {
    fun String.toTitleCase(): String {
        return this.split('_')
            .joinToString(" ") { it.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() } }
    }
}
