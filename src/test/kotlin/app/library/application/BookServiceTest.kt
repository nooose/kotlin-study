package app.library.application

import app.library.application.dto.book.BookRequest
import app.library.domain.book.BookRepository
import app.library.domain.book.BookType
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BookServiceTest @Autowired constructor(
    private val bookService: BookService,
    private val bookRepository: BookRepository,
) {

    @AfterEach
    fun clean() {
        bookRepository.deleteAll()
    }

    @Test
    fun `책 등록이 정상 동작한다`() {
        val request = BookRequest("이상한 나라의 앨리스", BookType.COMPUTER)

        bookService.saveBook(request)

        val books = bookRepository.findAll()
        assertThat(books).hasSize(1)
        assertThat(books[0].name).isEqualTo("이상한 나라의 앨리스")
        assertThat(books[0].type).isEqualTo(BookType.COMPUTER)
    }
}

