package coroutines

import kotlinx.coroutines.*
import java.util.concurrent.Executors

suspend fun main() {
    val job = CoroutineScope(Dispatchers.Default).launch {
        delay(1_000L)
        printWithThread("Job 1")
        coroutineContext + CoroutineName("noose 코루틴")
    }
    job.join()

    val threadPool = Executors.newFixedThreadPool(1)
    CoroutineScope(threadPool.asCoroutineDispatcher()).launch {
        printWithThread("새로운 코루틴")
    }
}

class AsyncLogic {
    private val scope = CoroutineScope(Dispatchers.Default)

    fun doSomething() {
        scope.launch {
            // 무언가 코루틴이 시작되어 작업!
        }
    }

    fun destroy() {
        scope.cancel()
    }
}
