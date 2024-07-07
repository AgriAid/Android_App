package com.ryan.agriaid.utility

fun parseResultsString(results: String): List<Pair<String, Double>> {
    val regex = "\\(([^,]+), ([^\\)]+)\\)".toRegex()
    return regex.findAll(results).map { matchResult ->
        val (name, value) = matchResult.destructured
        name to value.toDouble()
    }.toList()
}
