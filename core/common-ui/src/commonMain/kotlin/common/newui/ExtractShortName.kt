package common.newui

fun generateAcronym(fullName: String): String {
    val ignoreWords = setOf("of", "and", "the", "for", "with", "in", "on", "at", "by", "an")

    return fullName.split(" ")
        .filter { it.isNotEmpty() && it.lowercase() !in ignoreWords }
        .map { it[0].uppercaseChar() }
        .joinToString("")
}

