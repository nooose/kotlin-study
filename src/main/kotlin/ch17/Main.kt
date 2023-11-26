package ch17


fun main() {
    val fruits = listOf(
        Fruit("사과", 1_000),
        Fruit("사과", 1_500),
        Fruit("바나나", 2_000),
        Fruit("바나나", 2_500),
        Fruit("키위", 3_000),
        Fruit("키위", 3_500),
        Fruit("수박", 4_000),
        Fruit("수박", 5_000),
        Fruit("수박", 10_000)
    )

    val isApple = fun(fruit: Fruit): Boolean {
        return fruit.name == "사과"
    }

    val isApple2 = {fruit: Fruit -> fruit.name == "사과"}
    val filtered = filterFruits(fruits) { it.name == "사과" }
    println(filtered)
}

// 전통적인 고전 방식
private fun filterFruits(
    fruits: List<Fruit>, filter: (Fruit) -> Boolean
): List<Fruit> {
    val results = mutableListOf<Fruit>()
    for (fruit in fruits) {
        if (filter.invoke(fruit)) {
            results.add(fruit)
        }
    }
    return results
}


