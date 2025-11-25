package ua.edu.cdu.boris.newnav2025.repository

import ua.edu.cdu.boris.newnav2025.model.Author
import ua.edu.cdu.boris.newnav2025.model.Book
import ua.edu.cdu.boris.newnav2025.model.IListable

interface AppRepository {
    fun getAllData(): List<IListable>

    suspend fun getAllAuthors(): List<Author>

    suspend fun getAllBooksWithAuthors(): List<Book>

    suspend fun insertAuthors(authors: List<Author>)

    suspend fun insertBooks(books: List<Book>)

    suspend fun clearBooks()

    suspend fun clearAuthors()
}