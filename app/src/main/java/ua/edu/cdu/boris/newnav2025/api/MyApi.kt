package ua.edu.cdu.boris.newnav2025.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ua.edu.cdu.boris.newnav2025.model.Author
import ua.edu.cdu.boris.newnav2025.model.Book

interface MyApi {
    @GET("books.php")
    suspend fun getBooks(@Query("number") num: Int): Response<List<Book>>

    @GET("authors.php")
    suspend fun getAuthors(@Query("number") num: Int): Response<List<Author>>

    @GET("bookswithauthors.php")
    suspend fun getBooksWithAuthors(
        @Query("numauthors") numAuthors: Int,
        @Query("numbooks") numBooks: Int
    ): Response<List<Book>>
}