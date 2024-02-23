package coroutines

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main(): Unit = runBlocking {
    val time = measureTimeMillis {
        val job1 = async { apiCall1() }
        val job2 = async { apiCall2() }
        printWithThread(job1.await() + job2.await())
    }
    printWithThread("소요 시간: ${time}ms")
}

fun example5(): Unit = runBlocking {
    val job = async {
        3 + 5
    }
    val result = job.await()
    printWithThread(result)
}

fun example4(): Unit = runBlocking {
    val job1 = launch {
        delay(1_000L)
        printWithThread("Job 1")
    }
    job1.join()

    val job2 = launch {
        delay(1_000L)
        printWithThread("Job 2")
    }
}


fun example3(): Unit = runBlocking {
    val job = launch {
        (1..5).forEach {
            printWithThread(it)
            delay(500)
        }
    }

    delay(1_000L)
    job.cancel()
}

fun example2(): Unit = runBlocking {
    val job = launch(start = CoroutineStart.LAZY) {
            printWithThread("Hello Launch")
    }

    delay(1_000L)
    job.start()
}

fun example1() {
    runBlocking {
        printWithThread("START")
        launch {
            delay(2_000L)
            printWithThread("LAUNCH END")
        }
        printWithThread("END")
    }
}
