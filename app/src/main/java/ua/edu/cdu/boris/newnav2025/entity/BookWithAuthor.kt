package ua.edu.cdu.boris.newnav2025.entity

import androidx.room.Embedded
import androidx.room.Relation

data class BookWithAuthor(
    @Embedded val book: BookEntity,
    @Relation(
        parentColumn = "author_id",
        entityColumn = "author_id"
    )
    val author: AuthorEntity
)
