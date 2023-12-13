package uk.ac.tees.mad.b1970805.viewmodel

fun isValidNumberFormat(number: String): Boolean {
    return number.startsWith("+44")
}

fun isValidNarrationFormat(narration: String): Boolean {
    val regex = Regex("^[a-zA-Z0-9 ]+\$")
    return regex.matches(narration)
}