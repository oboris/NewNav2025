package ua.edu.cdu.boris.newnav2025.repository

import ua.edu.cdu.boris.newnav2025.model.Author
import ua.edu.cdu.boris.newnav2025.model.Book
import ua.edu.cdu.boris.newnav2025.model.IListable

interface AppRepository {
    suspend fun getAllData(): List<IListable>

    suspend fun getAllAuthors(): List<Author>

    suspend fun getAllBooksWithAuthors(): List<Book>

    suspend fun getAllBooksWithAuthorsV2(): List<Book>

    suspend fun insertAuthors(authors: List<Author>)

    suspend fun insertBooks(books: List<Book>)

    suspend fun clearBooks()

    suspend fun clearAuthors()

    suspend fun deleteBook(book: Book)

    suspend fun deleteAuthor(author: Author)

    suspend fun updateBook(book: Book)
}