package ua.edu.cdu.boris.newnav2025.repository

import ua.edu.cdu.boris.newnav2025.model.Author
import ua.edu.cdu.boris.newnav2025.model.Book
import ua.edu.cdu.boris.newnav2025.model.IListable

interface AppRepository {
    fun getAllData(): List<IListable>

    fun insertAuthors(authors: List<Author>)

    fun insertBooks(books: List<Book>)
}