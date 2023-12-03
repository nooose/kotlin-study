package app.library.application

import app.library.application.dto.book.BookLoanRequest
import app.library.application.dto.book.BookRequest
import app.library.application.dto.book.BookReturnRequest
import app.library.application.dto.book.BookStatResponse
import app.library.domain.book.Book
import app.library.infrastructure.BookQuerydslRepository
import app.library.domain.book.BookRepository
import app.library.domain.user.UserLoanStatus
import app.library.domain.user.UserRepository
import app.library.infrastructure.UserLoanHistoryQuerydslRepository
import app.library.util.fail
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookService(
    private val bookRepository: BookRepository,
    private val userRepository: UserRepository,
    private val bookQuerydslRepository: BookQuerydslRepository,
    private val userLoanHistoryQuerydslRepository: UserLoanHistoryQuerydslRepository,
) {

    @Transactional
    fun saveBook(request: BookRequest) {
        val newBook = Book(request.name, request.type)
        bookRepository.save(newBook)
    }

    @Transactional
    fun loanBook(request: BookLoanRequest) {
        val book = bookRepository.findByName(request.bookName) ?: fail()
        if (userLoanHistoryQuerydslRepository.find(request.bookName, UserLoanStatus.LOANED) != null) {
            throw IllegalArgumentException("진작 대출되어 있는 책입니다.")
        }

        val user = userRepository.findByName(request.userName) ?: fail()
        user.loanBook(book)
    }

    @Transactional
    fun returnBook(request: BookReturnRequest) {
        val user = userRepository.findByName(request.userName) ?: fail()
        user.returnBook(request.bookName)
    }

    @Transactional(readOnly = true)
    fun countLoanedBook(): Int {
        return userLoanHistoryQuerydslRepository.count(UserLoanStatus.LOANED).toInt()
    }

    @Transactional(readOnly = true)
    fun getBookStatistics(): List<BookStatResponse> {
        return bookQuerydslRepository.getStatus()
    }
}
