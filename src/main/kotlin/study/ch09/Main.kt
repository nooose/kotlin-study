package study.ch09

fun main() {
    val person1 = Person("noose", 100)
    println(person1.name)
    person1.age = 10
    println(person1.age)

    val person2 = Person("noose")
    println(person2.name)
    println(person2.age)

    val person3 = Person()
    println(person3.doubleName)
    println(person3.age)
    println(person3.isAdult)
}
