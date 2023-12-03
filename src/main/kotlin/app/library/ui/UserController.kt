package app.library.ui

import app.library.application.UserService
import app.library.application.dto.user.UserCreateRequest
import app.library.application.dto.user.UserLoanHistoryResponse
import app.library.application.dto.user.UserResponse
import app.library.application.dto.user.UserUpdateRequest
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService,
) {

    @PostMapping("/users")
    fun saveUser(@RequestBody request: UserCreateRequest) {
        userService.saveUser(request)
    }

    @GetMapping("/users")
    fun getUsers(): List<UserResponse> {
        return userService.getUsers()
    }

    @PutMapping("/users")
    fun returnBook(@RequestBody request: UserUpdateRequest) {
        userService.updateUsername(request)
    }

    @DeleteMapping("/users")
    fun deleteUser(@RequestParam name: String) {
        userService.deleteUser(name)
    }

    @GetMapping("/users/loan")
    fun getUserLoanHistories(): List<UserLoanHistoryResponse> {
        return userService.getUserLoanHistories()
    }
}
