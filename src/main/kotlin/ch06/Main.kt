package ch06

/**
 * 등차수열을 이용
 *
 * 변수 함수이름 arguments
 *
 * infix 중위 호출 함수
 * @see downTo
 * @see step
 */
fun main() {
    for (i in 1..3) {
        println(i)
    }

    for (i in 3 downTo 1) {
        println(i)
    }

    for (i in 1..5 step 2) {
        println(i)
    }
}
