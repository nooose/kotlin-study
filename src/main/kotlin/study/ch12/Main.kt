package study.ch12

fun main() {
    // 정적 팩터리 메소드 역할을 하는 companion object
    val baby = Person.newBaby("noose")
    println("이름: ${baby.name}, 나이: ${baby.age}")

    // 싱글톤
    Singleton.a = 10
    println(Singleton.a)

    // 익명 클래스
    moveSomething(object : Movable {
        override fun move() {
            println("움직여")
        }
    })
}

class Person private constructor(
    var name: String,
    var age: Int
) {
    companion object Factory : Log {
        private const val MIN_AGE = 1
        fun newBaby(name: String): Person {
            log()
            return Person(name, MIN_AGE)
        }

        override fun log() {
            println("companion object 입니다.")
        }
    }
}

object Singleton {
    var a: Int = 0
}

private fun moveSomething(movable: Movable) {
    movable.move()
}
