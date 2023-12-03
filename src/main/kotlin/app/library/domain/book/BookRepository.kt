package app.library.domain.book

import app.library.application.dto.book.BookStatResponse
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface BookRepository : JpaRepository<Book, Long> {
    fun findByName(bookName: String): Book?

    @Query("""
        select NEW app.library.application.dto.book.BookStatResponse(b.type, count(b.id)) 
        from Book b
        group by b.type
    """)
    fun getStatus(): List<BookStatResponse>
}
