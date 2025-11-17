package ua.edu.cdu.boris.newnav2025.utils

import ua.edu.cdu.boris.newnav2025.entity.AuthorEntity
import ua.edu.cdu.boris.newnav2025.entity.BookEntity
import ua.edu.cdu.boris.newnav2025.model.Author
import ua.edu.cdu.boris.newnav2025.model.Book


fun Author.asAuthorEntity() = AuthorEntity(
    name = name,
    birthday = birthday
)

fun AuthorEntity.asAuthor() = Author(
    name = name,
    birthday = birthday
)

fun Book.asBookEntity() = BookEntity(
    title = title,
    pages = pages,
    author = author
)

fun BookEntity.asBook() = Book(
    title = title,
    pages = pages,
    author = author
)