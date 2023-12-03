package app.library.domain.user

interface UserRepositoryCustom {

    fun findAllWithHistories(): List<User>
}
