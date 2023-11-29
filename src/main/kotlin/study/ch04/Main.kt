package study.ch04


fun main() {
    val money1 = Money(3_000)
    val money2 = Money(1_000)
    val money3 = Money(3_000)

    println(money1 > money2) // operator
    println(money1 == money3)
    println(money1 === money3)
    println(1 in listOf(1, 2, 3))
    println(money1 + money2) // operator
}
