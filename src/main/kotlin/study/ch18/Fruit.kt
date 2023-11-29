package study.ch18

data class Fruit(
    val id: Int,
    val name: String,
    val price: Int
) {

    val isCheapPrice: Boolean
        get() = price <= 3_000
}
