package ch11

class Test(
    val number1: Int,
    number2: Int
) {

    var number2 = number2
        private set

    fun update(number2: Int) {
        this.number2 = number2
    }
}
