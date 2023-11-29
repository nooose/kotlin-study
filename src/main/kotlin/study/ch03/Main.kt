package study.ch03

import study.ch01.Person

fun main() {
    // 명시적 형변환
    val number1: Int? = 3
    val number2: Long = number1?.toLong() ?: 0L

    printIfPerson(Person("noose"))
    printIfPerson(null)
    println("ABC"[0])
}

// 타입 캐스팅
fun printIfPerson(obj: Any?) {
//    if (obj is Person) {
//        println(obj) // 스마트 캐스트
//    }
//
//    if (obj !is Person) {
//        println(null)
//    }

//    val person = obj as? Person // 예외 처리

    val person = obj as? Person // 안전한 타입 형변환
    println("이름: ${person?.name ?: "empty"}")
}
