package app.library.application.dto.book

import app.library.domain.book.BookType

data class BookRequest(
    val name: String,
    val type: BookType,
)
