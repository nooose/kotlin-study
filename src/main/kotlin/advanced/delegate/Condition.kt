package advanced.delegate

fun main() {
    val condition = ConditionPro(ConditionImpl("hello", 10))
    println(condition.age)
    println(condition.name)
    condition.test()
}

interface Condition {
    val name: String
    val age: Int
    fun test()
}

class ConditionImpl(
    override val name: String,
    override val age: Int
) : Condition {
    override fun test() {
        println("Hello Test")
    }
}

class ConditionPro(
    private val condition: Condition
) : Condition by condition
