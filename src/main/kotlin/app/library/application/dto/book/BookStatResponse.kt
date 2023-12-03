package app.library.application.dto.book

import app.library.domain.book.BookType

data class BookStatResponse(
    val type: BookType,
    val count: Long,
)
