package study.ch14

fun main() {
    val personDto1 = PersonDto("이름1", 100)
    val personDto2 = PersonDto("이름1", 100)

    println(personDto1 == personDto2)
}

data class PersonDto(
    val name: String,
    val age: Int
)
