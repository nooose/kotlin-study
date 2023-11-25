package ch04

class Money(
    val value: Int
) {
    override fun equals(other: Any?): Boolean {
        val that = other as? Money
        return value == that?.value
    }

    override fun toString(): String {
        return value.toString()
    }

    operator fun plus(other: Money): Any {
        return Money(value + other.value)
    }

    operator fun compareTo(that: Money): Int {
        return value - that.value
    }
}
