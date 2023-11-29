package app.calculator

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class CalculatorTest {

    companion object {
        @JvmStatic
        @BeforeAll
        fun beforeAll() {
            println("모든 테스트 시작 전")
        }

        @JvmStatic
        @AfterAll
        fun afterAll() {
            println("모든 테스트 종료 후")
        }
    }

    @Test
    fun `덧셈 테스트`() {
        val calculator = Calculator(5)

        calculator.add(3)

        assertThat(calculator.number).isEqualTo(8)
    }

    @Test
    fun `뺄셈 테스트`() {
        val calculator = Calculator(5)

        calculator.minus(3)

        assertThat(calculator.number).isEqualTo(2)
    }

    @Test
    fun `곱셈 테스트`() {
        val calculator = Calculator(5)

        calculator.multiply(3)

        assertThat(calculator.number).isEqualTo(15)
    }

    @Test
    fun `나눗셈 테스트`() {
        val calculator = Calculator(5)

        calculator.divide(3)

        assertThat(calculator.number).isEqualTo(1)
    }

    @Test
    fun `0으로 나누면 예외를 던진다`() {
        val calculator = Calculator(5)

        assertThrows<IllegalArgumentException> { calculator.divide(0) }
            .apply { assertThat(message).isEqualTo("0으로 나눌 수 없습니다.") }
    }
}
