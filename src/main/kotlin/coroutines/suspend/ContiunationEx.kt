package coroutines.suspend

import kotlinx.coroutines.delay

suspend fun main() {
    val userService = UserService()
    val result = userService.findUser(1L)
    println(result)
}

interface Continuation {
    suspend fun resumeWith(data: Any?)
}

class UserService {
    private val userProfileRepository = UserProfileRepository()
    private val userImageRepository = UserImageRepository()

    /**
     * Continuation 을 전달하며 Callback 으로 활용한다.
     * Continuation Passing Style (CPS)
     */
    private abstract class FindUserContinuation() : Continuation {
        var label = 0
        var profile: Profile? = null
        var image: Image? = null
    }

//        val profile = userProfileRepository.findProfile(userId)
//        val image = userImageRepository.findImage(profile)
//        return UserDto(profile, image)
    suspend fun findUser(userId: Long, continuation: Continuation? = null): UserDto {
        val stateMachine = continuation as? FindUserContinuation ?: object : FindUserContinuation() {
            override suspend fun resumeWith(data: Any?) {
                when (label) {
                    0 -> {
                        profile = data as Profile
                        label = 1
                    }
                    1 -> {
                        image = data as Image
                        label = 2
                    }
                }
                findUser(userId, this)
            }
        }

        when (stateMachine.label) {
            0 -> {
                println("프로필 가져오기")
                userProfileRepository.findProfile(userId, stateMachine)
            }
            1 -> {
                println("이미지 가져오기")
                userImageRepository.findImage(stateMachine.profile!!, stateMachine)
            }
        }

        return UserDto(stateMachine.profile!!, stateMachine.image!!)
    }
}

data class UserDto(
    val profile: Profile,
    val image: Image,
)

class Profile
class Image

class UserProfileRepository {
    suspend fun findProfile(userId: Long, continuation: Continuation) {
        delay(100L)
        continuation.resumeWith(Profile())
    }
}

class UserImageRepository {
    suspend fun findImage(profile: Profile, continuation: Continuation) {
        delay(100L)
        continuation.resumeWith(Image())
    }
}
