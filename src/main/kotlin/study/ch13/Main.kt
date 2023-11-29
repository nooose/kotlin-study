package study.ch13

fun main() {

}

class House(
    private val address: String,
    private val livingRoom: LivingRoom
) {
    // 권장되지 않는 클래스 안 클래스
    inner class LivingRoom( // 굳이 바깥 클래스를 사용한다면 inner 키워드를 사용한다.
        private val area: Double
    ) {
        val address: String
            get() = this@House.address
    }
}
