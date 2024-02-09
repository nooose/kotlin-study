package advanced.extra

import kotlin.reflect.KClass

class ResultWrapper<T>(
    private val result: Result<T>,
    private val knownExceptions: MutableList<KClass<out Throwable>>,
) {

    fun toResult(): Result<T> {
        return this.result
    }

    fun onError(vararg exceptions: KClass<out Throwable>, action: (Throwable) -> Unit): ResultWrapper<T> {
        this.result.exceptionOrNull()?.let {
            val kClass = it::class
            if (kClass in exceptions && kClass !in this.knownExceptions) {
                action(it)
            }
        }
        return this
    }
}

fun <T> Result<T>.onError(vararg exceptions: KClass<out Throwable>, action: (Throwable) -> Unit): ResultWrapper<T> {
    exceptionOrNull()?.let {
        if (it::class in exceptions) {
            action(it)
        }
    }
    return ResultWrapper(this, exceptions.toMutableList())
}

fun main() {
    runCatching { println() }
        .onError(IllegalArgumentException::class, IllegalStateException::class) {
            println("IllegalXXX 예외 발생")
        }
        .onError(ArithmeticException::class) {
            println("ArithmeticException 예외 발생")
        }
}
