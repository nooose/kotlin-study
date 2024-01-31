package sequence

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.BenchmarkMode
import org.openjdk.jmh.annotations.Mode
import org.openjdk.jmh.annotations.OutputTimeUnit
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State
import java.util.concurrent.TimeUnit
import kotlin.random.Random

fun main() {
    val fruits = listOf(
        MyFruit("사과", 1000L),
        MyFruit("바나나", 3000L),
    )

    // 한 원소에 대해 모든 연산을 하고 다음 원소로 넘어간다.
    // 최종 연산까지 계산 자체를 미리하지 않는다. -> 지연 연산
    val avg = fruits.asSequence()
        .filter { it.name == "사과" }
        .map { it.price }
        .take( 10_000 )
        .average()
}

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
open class SequenceTest {
    private val fruits = mutableListOf<MyFruit>()

    @Setup
    fun init() {
        (1..2_000_000).forEach { _ -> fruits.add(MyFruit.random())}
    }

    @Benchmark
    fun kotlinSequence() {
        val avg = fruits.asSequence()
            .filter { it.name == "사과" }
            .map { it.price }
            .take( 10_000 )
            .average()
    }

    @Benchmark
    fun kotlinIterator() {
        val avg = fruits
            .filter { it.name == "사과" }
            .map { it.price }
            .take( 10_000 )
            .average()
    }
}


data class MyFruit(
    val name: String,
    val price: Long,
) {

    companion object {
        private val NAME_CANDIDATES = listOf("사과", "바나나", "수박", "체리", "오렌지")
        fun random(): MyFruit {
            val randNum1 = Random.nextInt(0, 5)
            val randNum2 = Random.nextLong(1000, 200001)
            return MyFruit(
                name = NAME_CANDIDATES[randNum1],
                price = randNum2
            )
        }
    }
}
