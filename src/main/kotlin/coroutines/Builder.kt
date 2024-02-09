package coroutines

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() {
    runBlocking {
        printWithThread("START")
        launch {
            delay(2_000L)
            printWithThread("LAUNCH END")
        }
    }
    printWithThread("END")

    runBlocking {
        val job = launch(start = CoroutineStart.LAZY) {
            printWithThread("Hello, launch")
        }
        delay(1_000L)
        job.start()
    }

    runBlocking {
        val job = launch {
            (1..5).forEach {
                printWithThread(it)
                delay(500L)
            }
        }
        delay(1_000L)
        job.cancel()
    }

    runBlocking {
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

    runBlocking {
        val job = async {
            3 + 5
        }
        val result = job.await()
        printWithThread(result)
    }

    runBlocking {
        val time = measureTimeMillis {
            val job1 = async { apiCall1() }
            val job2 = async { apiCall2() }
            printWithThread(job1.await() + job2.await())
        }
        printWithThread("소요 시간: $time")
    }


    runBlocking {
        val time = measureTimeMillis {
            val job1 = async { apiCall3() }
            val job2 = async { apiCall4(job1.await()) }
            printWithThread(job2.await())
        }
        printWithThread("소요 시간: $time")
    }
}

suspend fun apiCall1(): Int {
    delay(1_000L)
    return 1
}

suspend fun apiCall2(): Int {
    delay(1_000L)
    return 2
}

suspend fun apiCall3(): Int {
    delay(1_000L)
    return 3
}

suspend fun apiCall4(num: Int): Int {
    delay(1_000L)
    return num + 4
}
