package advanced.dsl

import java.time.LocalDate

class OperatorOverloading {

}

data class Point(
    val x: Int,
    val y: Int,
) {
    fun zeroPointSymmetry(): Point {
        return Point(-x, -y)
    }

    operator fun unaryMinus(): Point {
        return Point(-x, -y)
    }

    operator fun inc(): Point {
        return Point(x + 1, y + 1)
    }

}


fun main() {
    var point = Point(20, -10)
    println(point.zeroPointSymmetry())
    println(-point)
    println(++point)

    println(LocalDate.of(2024, 1, 1).plusDays(3))
    println(LocalDate.of(2024, 1, 1) + Days(3))
    println(LocalDate.of(2024, 1, 1) + 3.day)
    // 오버로딩 가능한 Operator 목록은 https://kotlinlang.org/docs/operator-overloading.html 에서 확인 가능
}

data class Days(val day: Long)

val Int.day: Days
    get() = Days(this.toLong())

operator fun LocalDate.plus(days: Days): LocalDate {
    return this.plusDays(days.day)
}
