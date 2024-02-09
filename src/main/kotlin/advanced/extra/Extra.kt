package advanced.extra

import kotlin.system.measureTimeMillis

tailrec fun factorialV2(n: Int, current: Int = 1): Int {
    return if (n <= 1) {
        current
    } else {
        factorialV2(n - 1, n * current)
    }
}

fun main() {
    repeat(3) {
        println("반복")
    }

    val timeMillis = measureTimeMillis {
        var result = 0
        for (index in 0..1000000000) {
            result += index
        }
    }
    println("소요시간: $timeMillis")

    require(timeMillis < 3000) { "시간 초과" }
    check(timeMillis < 3000 ) { "IllegalStateException" }

    val result = runCatching { 1 / 0 }
    println("message: ${result}, result: ${result.isSuccess}")

    println(factorialV2(5))

    val userId = 1L
    val bookId = 2L
    handle(userId, bookId)

    val inlineUserId = Id<User>(1L)
    val inlineBookId = Id<Book>(2L)
    handleV2(inlineUserId, inlineBookId)

    try {

    } catch (e: Exception) {
        when (e) {
            is IllegalArgumentException,
            is IllegalStateException, -> TODO()
            is NumberFormatException -> TODO()
        }
        throw e
    }
}

class User(
    val id: Id<User>,
    val name: String,
)

class Book(
    val id: Id<Book>,
    val author: String,
)

fun handle(userId: Long, bookId: Long) { }
fun handleV2(userId: Id<User>, bookId: Id<Book>) { }

@JvmInline
value class Id<T>(val id: Long)

@JvmInline
value class Money(val value: Long) {
    init {
        require(value >= 0)
    }
}
