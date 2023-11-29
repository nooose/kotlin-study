package study.ch02

import java.lang.IllegalArgumentException

fun main() {
    // safe call
    println(startsWithA1("A"))
    println(startsWithA2(null))
    println(startsWithA3(null))
    println(startsWith("A"))
}

fun startsWithA1(str: String?): Boolean {
    // Elvis 연산
    return str?.startsWith("A") ?: throw IllegalArgumentException("null 허용하지 않음")
}

fun startsWithA2(str: String?): Boolean? {
    return str?.startsWith("A")
}

fun startsWithA3(str: String?): Boolean {
    return str?.startsWith("A") ?: false
}

fun startsWith(str: String?): Boolean {
    // null 아님 단언
    return str!!.startsWith("A")
}
