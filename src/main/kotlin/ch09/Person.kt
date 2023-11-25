package ch09

import java.lang.IllegalArgumentException

class Person(
    val name: String,
    var age: Int
) {

    init {
        if (age <= 0) {
            throw IllegalArgumentException("나이는 ${age}일 수 없습니다.")
        }
        println("초기화 블록")
    }

    // 부생성자보다는 default parameter를 권장한다
    // 써야할 일이 있다면 정적 팩터리 메소드를 사용한다.
    constructor(name: String): this(name, 1) {
        println("첫 번째 부생성자")
    }
    constructor(): this("홍길동") {
        println("두 번째 부생성자")
    }

    val doubleName: String
        get() = this.name.repeat(2)

//    fun isAdult(): Boolean {
//        return this.age >= 20
//    }
    // 함수 대신 프로퍼티처럼 만들 수 있다.
    val isAdult: Boolean
        get() = this.age >= 20
}
