package ua.edu.cdu.boris.newnav2025.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import ua.edu.cdu.boris.newnav2025.entity.AuthorEntity
import ua.edu.cdu.boris.newnav2025.entity.BookEntity
import ua.edu.cdu.boris.newnav2025.entity.BookWithAuthor
import ua.edu.cdu.boris.newnav2025.entity.BookWithAuthorV2

@Dao
interface AppDao {

//Authors DAO
    @Query("SELECT * FROM authors")
    suspend fun getAllAuthors(): List<AuthorEntity>

    @Query("SELECT * FROM authors WHERE author_id = :id")
    suspend fun getAuthorById(id: Int): AuthorEntity

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertAllAuthors(authors: List<AuthorEntity>)

    @Insert
    suspend fun insertAuthor(author: AuthorEntity)

    @Delete
    suspend fun deleteAuthor(author: AuthorEntity)

    @Query("DELETE FROM authors")
    suspend fun deleteAllAuthors()

//Books DAO
    @Transaction
    @Query("SELECT * FROM books")
    suspend fun getAllBooks(): List<BookWithAuthor>

    @Transaction
    @Query("SELECT * FROM books Join authors On books.author_id = authors.author_id")
    suspend fun getAllBooksV2(): List<BookWithAuthorV2>

    @Insert
    suspend fun insertAllBooks(vararg books: BookEntity)

    @Insert
    suspend fun insertBook(book: BookEntity)

    @Delete
    suspend fun deleteBook(book: BookEntity)

    @Query("DELETE FROM books")
    suspend fun deleteAllBooks()

    @Transaction
    @Update
    suspend fun updateBook(book: BookEntity)
}