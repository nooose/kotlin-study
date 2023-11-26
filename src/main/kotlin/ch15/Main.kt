package ch15

fun main() {
    val array = arrayOf(10, 20, 30)
    val plus = array.plus(40)
    for ((idx, value) in plus.withIndex()) {
        println("$idx $value")
    }

    val numbers = listOf(10, 20, 30, 40)
    val emptyList = emptyList<Int>()

    val oldMap = mutableMapOf<Int, String>()
    oldMap[1] = "MONDAY"
    oldMap[2] = "TUESDAY"
    println(oldMap)

    val map = mapOf(1 to "MONDAY", 2 to "TUESDAY") // 중위 호출
    println(map)

    for (key in oldMap.keys) {
        println("$key : ${oldMap[key]}")
    }
}
