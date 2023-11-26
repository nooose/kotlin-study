package ch19

import ch18.Fruit
import ch19.A.print as printA
import ch19.B.print as printB

data class UltraSuperGuardianTribe(
    val name: String
)

typealias USGTMap = Map<String, UltraSuperGuardianTribe>

fun main() {
    printA()
    printB()

    val fruit = Fruit(1, "사과", 1_000)
    val (id, name) = fruit // 구조분해
    println("$id $name")
    // data 클래스가 아니라면 componentN 을 오버라이드해야함

    val result = listOf(1, 2, 3, 4, 5, 6, 7).map { it.takeIf { it < 5 } }
    println(result)
}
