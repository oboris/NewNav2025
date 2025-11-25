package ua.edu.cdu.boris.newnav2025.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Relation

data class BookWithAuthorV2(
    @ColumnInfo(name = "book_id")
    val id: Int = 0,

    @ColumnInfo(name = "book_title")
    val title: String,

    val pages: Int,

    @Embedded val author: AuthorEntity
)
