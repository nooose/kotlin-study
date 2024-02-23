package coroutines

import kotlinx.coroutines.*

fun main(): Unit = runBlocking {
    val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        printWithThread("예외")
    }

    val job = CoroutineScope(Dispatchers.Default).launch(exceptionHandler) { // launch 에만 exceptionHandler 를 적용할 수 있다.
        throw IllegalArgumentException()
    }

    delay(1_000L)
}


fun example(): Unit = runBlocking {
    CoroutineScope(Dispatchers.Default).launch {
        printWithThread("launch")
        throw IllegalArgumentException()
    }

    val job1 = CoroutineScope(Dispatchers.Default).async {
        printWithThread("async 1")
        throw IllegalArgumentException()
    }

    val job2 = async { // SupervisorJob()을 사용하면 예외를 전파하지 않음
        printWithThread("async 2")
        throw IllegalArgumentException() // 부모(main)에게 예외가 전파
    }

    delay(1_000L)
    job1.await() // async의 예외를 확인하기 위해
}

