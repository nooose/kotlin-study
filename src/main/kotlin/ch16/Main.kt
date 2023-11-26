package ch16

import java.time.LocalDate
import java.time.format.DateTimeFormatter


object MyDateUtils {
    val DEFAULT_FORMAT: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
}

// 확장 함수
fun String.lastChar(): Char {
    return this[this.length - 1]
}

fun Person.nextYearAge(): Int {
    println("확장 함수")
    return this.age + 1
}

fun LocalDate.defaultFormat(): String {
    return this.format(MyDateUtils.DEFAULT_FORMAT)
}

// 중위 함수
infix fun Int.add(other: Int): Int {
    return this + other
}

fun main() {
    println("test".lastChar())

    val noose = Person("noose", 10)
    println(noose.nextYearAge())

    println(LocalDate.now().defaultFormat())
    println(10 add 20)
}
