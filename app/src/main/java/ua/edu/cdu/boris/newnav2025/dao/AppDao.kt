package ua.edu.cdu.boris.newnav2025.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ua.edu.cdu.boris.newnav2025.entity.AuthorEntity
import ua.edu.cdu.boris.newnav2025.entity.BookEntity

@Dao
interface AppDao {
    @Query("SELECT * FROM authors")
    fun getAllAuthors(): List<AuthorEntity>

    @Query("SELECT * FROM authors WHERE author_id = :id")
    fun getAuthorById(id: Int): AuthorEntity

    @Insert
    fun insertAllAuthors(authors: List<AuthorEntity>)

    @Insert
    fun insertAuthor(author: AuthorEntity)

    @Delete
    fun deleteAuthor(author: AuthorEntity)

    @Query("DELETE FROM authors")
    fun deleteAllAuthors()

    @Query("SELECT * FROM books")
    fun getAllBooks(): List<BookEntity>

    @Insert
    fun insertAllBooks(vararg books: BookEntity)

    @Insert
    fun insertBook(book: BookEntity)

    @Delete
    fun deleteBook(book: BookEntity)

    @Query("DELETE FROM books")
    fun delAllBooks()
}