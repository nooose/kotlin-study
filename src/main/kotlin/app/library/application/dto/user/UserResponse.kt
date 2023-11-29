package app.library.application.dto.user

import app.library.domain.user.User

data class UserResponse(
    val id: Long,
    val name: String,
    val age: Int?,
) {

    companion object {
        fun from(entity: User): UserResponse {
            return UserResponse(
                id = entity.id!!,
                name = entity.name,
                age = entity.age
            )
        }
    }
}
