package app.library.application.dto.book

data class BookLoanRequest(
    val userName: String,
    val bookName: String,
)
