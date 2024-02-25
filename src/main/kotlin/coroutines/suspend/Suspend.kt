package coroutines.suspend

import coroutines.printWithThread
import kotlinx.coroutines.*

fun main(): Unit = runBlocking {
    val result1 = call1()
    val result2 = call2(result1)
    printWithThread(result2)
}

suspend fun call1(): Int {
    return CoroutineScope(Dispatchers.Default).async {
        Thread.sleep(1_000L)
        100
    }.await()
}

suspend fun call2(number: Int): Int {
    return CoroutineScope(Dispatchers.Default).async {
        Thread.sleep(1_000L)
        number * 2
    }.await()
}
