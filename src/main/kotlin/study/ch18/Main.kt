package study.ch18

fun List<Fruit>.cheapFilter(): List<Fruit> {
    return this.filter(Fruit::isCheapPrice)
}

fun main() {
    val fruits = listOf(
        Fruit(1, "사과", 1_000),
        Fruit(2, "사과", 1_500),
        Fruit(3, "바나나", 2_000),
        Fruit(4, "바나나", 2_500),
        Fruit(5, "키위", 3_000),
        Fruit(6, "키위", 3_500),
        Fruit(7, "수박", 4_000),
        Fruit(8, "수박", 5_000),
        Fruit(9, "수박", 10_000)
    )

    println(fruits.count())
    println(fruits.firstOrNull() ?: Fruit(0, "", 0))
    println(fruits.last())
    println(fruits.sortedBy { it.price })
    println(fruits.sortedByDescending { it.price })
    println(fruits.distinctBy { it.name }.map { it.name })
    println(fruits.groupBy { it.name })
    println(fruits.groupBy({ it.name }, { it.price }))
    println(fruits.associateBy({ it.id }, { it.price }))

    val nestedFruits = listOf(
        listOf(
            Fruit(1, "사과", 1_000),
            Fruit(2, "사과", 1_500),
        ),
        listOf(
            Fruit(3, "바나나", 2_000),
            Fruit(4, "바나나", 2_500),
        ),
        listOf(
            Fruit(5, "키위", 3_000),
            Fruit(6, "키위", 3_500),

            ),
        listOf(
            Fruit(7, "수박", 4_000),
            Fruit(8, "수박", 5_000),
            Fruit(9, "수박", 10_000)
        )
    )

    println(nestedFruits.flatten())
    val cheapFlatten = nestedFruits.flatMap { it.cheapFilter() }
    println(cheapFlatten)
}
