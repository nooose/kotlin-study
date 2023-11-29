package study.ch08

fun main() {
    repeat("Hello")
    repeat("Hello", useNewLine = false) // named argument

    println()
    printAll("A", "B", "C")
    val array = arrayOf("A", "B", "C")
    printAll(*array) // 스프레드 연산자
}

// = 을 사용하면 반환 타입을 생략 가능
fun max(number1: Int, number2: Int) = if (number1 > number2) number1 else number2

// default parameter
fun repeat(str: String, number: Int = 3, useNewLine: Boolean = true) {
    for (i in 1..number) {
        if (useNewLine) {
            println(str)
        } else {
            print(str)
        }
    }
}

fun printAll(vararg strings: String) {
    for (str in strings) {
        println(str)
    }
}
