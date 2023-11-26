package ch16

class Person(
    val person: String,
    val age: Int
) {

    fun nextYearAge(): Int {
        return this.age + 1
    }
}
