package ua.edu.cdu.boris.newnav2025.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded

data class BookWithAuthorV2(
    @ColumnInfo(name = "book_id")
    val id: Int = 0,

    @ColumnInfo(name = "book_title")
    val title: String,

    val pages: Int,

    @Embedded val author: AuthorEntity
)
