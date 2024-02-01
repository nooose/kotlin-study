package advanced.function


fun main() {
    repeat(5) { println("Hello") } // println 까지 inline 처리
    iterateToAbleNonLocalReturn(listOf(1, 2, 3, 4, 5)) { num -> // inline 함수는 non-local return 을 허용하게 한다
        if (num == 3) {
            return // 제일 가까운 함수(main)를 리턴하기 때문에 의도한 대로 동작이 되지는 않는다
        }
        println(num)
    }

    /*
    iterateToUnableNonLocalReturn(listOf(1, 2, 3, 4, 5)) { num ->
        if (num == 3) {
            return
        }
        println(num)
    }
     */
}

inline fun iterateToAbleNonLocalReturn(numbers: List<Int>, exec: (Int) -> Unit) {
    for (num in numbers) {
        exec(num)
    }
}

inline fun iterateToUnableNonLocalReturn(numbers: List<Int>, crossinline exec: (Int) -> Unit) {
    for (num in numbers) {
        exec(num)
    }
}

inline fun repeat(times: Int, exec: () -> Unit) {
    for (i in 1..times) {
        exec()
    }
}
