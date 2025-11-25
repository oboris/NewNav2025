package ua.edu.cdu.boris.newnav2025.utils

import ua.edu.cdu.boris.newnav2025.entity.AuthorEntity
import ua.edu.cdu.boris.newnav2025.entity.BookEntity
import ua.edu.cdu.boris.newnav2025.entity.BookWithAuthor
import ua.edu.cdu.boris.newnav2025.entity.BookWithAuthorV2
import ua.edu.cdu.boris.newnav2025.model.Author
import ua.edu.cdu.boris.newnav2025.model.Book


fun Author.toEntity() = AuthorEntity(
    id = this.id,
    name = this.name,
    birthday = this.birthday
)

fun AuthorEntity.toDomain() = Author(
    id = this.id,
    name = this.name,
    birthday = this.birthday
)

fun Book.toEntity() = BookEntity(
    id = this.id,
    title = this.title,
    pages = this.pages,
    authorId = this.author.id
)

fun BookWithAuthor.toDomain() = Book(
    id = this.book.id,
    title = this.book.title,
    pages = this.book.pages,
    author = this.author.toDomain()
)

fun BookWithAuthorV2.toDomain() = Book(
    id = this.id,
    title = this.title,
    pages = this.pages,
    author = this.author.toDomain()
)