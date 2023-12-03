package app.library.infrastructure

import app.library.application.dto.book.BookStatResponse
import app.library.domain.book.QBook.book
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component

@Component
class BookQuerydslRepository(
    private val queryFactory: JPAQueryFactory,
) {
    fun getStatus(): List<BookStatResponse> {
        return queryFactory
            .select(
                Projections.constructor(
                    BookStatResponse::class.java,
                    book.type,
                    book.id.count()
                )
            )
            .from(book)
            .groupBy(book.type)
            .fetch()
    }
}
