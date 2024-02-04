package advanced.function

fun main() {
    // 람다식
    compute(5, 3) { a, b -> a + b }

    // 익명 함수
    compute(5, 3, fun(a: Int, b: Int) = a + b )
    compute(5, 3, fun(a, b) = a + b)

    // 익명 함수
    iterate(listOf(1, 2, 3, 4, 5), fun(num) {
        if (num == 3) {
            return
        }
        println(num)
    })

    /*
    iterate(listOf(1, 2, 3, 4, 5)) { num ->
        if (num == 3) {
           return // 람다에서는 리턴이 불가능, non-local return
        }
        println(num)
    }
    */

    println(5.add(10)) // 확장 함수를 이용한 함수 리터럴
}

fun compute(num1: Int, num2: Int, op: (Int, Int) -> Int): Int {
    return op(num1, num2)
}

fun iterate(numbers: List<Int>, exec: (Int) -> Unit) {
    for (number in numbers) {
        exec(number)
    }
}

//fun calculate(num1: Int, num2: Int, operator: Operator) = operator.calcFunc(num1, num2)
fun calculate(num1: Int, num2: Int, operator: Operator) = operator(num1, num2)

enum class Operator(
    private val operatorSymbol: Char,
    val calcFunc: (Int, Int) -> Int,
) {
    PLUS('+', { a, b -> a + b}),
    MINUS('-', { a, b -> a - b}),
    MULTIPLY('*', { a, b -> a * b}),
    DIVIDE('/', { a, b ->
        if (b == 0) {
            throw IllegalArgumentException("0으로 나눌 수 없습니다.")
        } else {
            a / b
        }
    }),;

    operator fun invoke(num1: Int, num2: Int): Int {
        return this.invoke(num1, num2)
    }
}


val add = fun Int.(other: Long): Int = this + other.toInt()
