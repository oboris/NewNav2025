package ua.edu.cdu.boris.newnav2025.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "books",
    foreignKeys = [
        ForeignKey(
            entity = AuthorEntity::class,
            parentColumns = arrayOf("author_id"),
            childColumns = arrayOf("author_id"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class BookEntity(
    @PrimaryKey(
        autoGenerate = true
    )
    @ColumnInfo(name = "book_id")
    val id: Int = 0,

    @ColumnInfo(name = "book_title")
    val title: String,

    val pages: Int,

    @ColumnInfo(name = "author_id", index = true)
    val authorId: Int
)
