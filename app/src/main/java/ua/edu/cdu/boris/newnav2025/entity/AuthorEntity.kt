package ua.edu.cdu.boris.newnav2025.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "authors"
)
data class AuthorEntity(
    @PrimaryKey(
        autoGenerate = true
    )
    @ColumnInfo(name = "author_id")
    val id: Int = 0,

    @ColumnInfo(name = "author_name")
    var name: String,

    val birthday: String
)
