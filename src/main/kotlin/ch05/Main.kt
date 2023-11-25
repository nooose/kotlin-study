package ch05

import java.lang.IllegalArgumentException

fun main() {
    val score = 70
    validateScore(score)
    println(getPassOrFail(score))
    println(getGrade(score))
    println(startsWithA("noose"))
    judgeNumber(score)
}

fun validateScore(score: Int) {
    if (score !in 0..100) {
        throw IllegalArgumentException("0 ~ 100 숫자만 가능합니다. - ${score}}")
    }
}

fun getPassOrFail(score: Int): String {
    return if (score >= 50) {
        "PASS"
    } else {
        "FAIL"
    }
}

fun getGrade(score: Int): String {
    return when (score) {
        in 90..99 -> "A"
        in 80..89 -> "B"
        in 70..79 -> "C"
        else -> "D"
    }
}

fun startsWithA(obj: Any): Boolean {
    return when (obj) {
        is String -> obj.startsWith("A")
        else -> false
    }
}

fun judgeNumber(number: Int) {
    return when (number) {
        1, 0, -1 -> println("많이 본 숫자입니다.")
        else -> println("1, 0, -1이 아닙니다.")
    }
}
