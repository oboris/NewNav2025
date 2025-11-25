package ua.edu.cdu.boris.newnav2025.repository

import ua.edu.cdu.boris.newnav2025.dao.AppDao
import ua.edu.cdu.boris.newnav2025.model.Author
import ua.edu.cdu.boris.newnav2025.model.AuthorList
import ua.edu.cdu.boris.newnav2025.model.Book
import ua.edu.cdu.boris.newnav2025.model.IListable
import ua.edu.cdu.boris.newnav2025.utils.toDomain
import ua.edu.cdu.boris.newnav2025.utils.toEntity

class AppRepositoryImpl(private val appDao: AppDao) : AppRepository {

    val myMegaAuthorList = listOf(
        Author(
            name = "Author 1",
            birthday = "01/01/2000"
        ),

        Author(
            name = "Author 2",
            birthday = "01/01/2010"
        ),

        Author(
            name = "Author 3",
            birthday = "02/02/2020"
        )
    )

    var myMegaBookList = listOf(
        Book(
            title = "Book 1",
            pages = 100,
            author = myMegaAuthorList[0]
        ),
        Book(
            title = "Book 2",
            pages = 110,
            author = myMegaAuthorList[0]
        ),
        Book(
            title = "Book 3",
            pages = 10,
            author = myMegaAuthorList[1]
        ),
        Book(
            title = "Book 4",
            pages = 300,
            author = myMegaAuthorList[0]
        ),
        Book(
            title = "Book 5",
            pages = 50,
            author = myMegaAuthorList[1]
        ),
        Book(
            title = "Book 6",
            pages = 100,
            author = myMegaAuthorList[2]
        ),
        Book(
            title = "Book 7",
            pages = 100,
            author = myMegaAuthorList[0]
        )
    )


    fun loadAuthors(): List<Author>{
        return myMegaAuthorList
    }

    fun loadBooks(): List<Book>{
        return myMegaBookList
    }

    override suspend fun getAllData(): List<IListable> {

//        val myMegaAuthorList = loadAuthors()
//
//        var myMegaList: List<IListable> = loadBooks()

        var myMegaList: List<IListable> = getAllBooksWithAuthors()

//        var myMegaList: List<IListable> = getAllBooksWithAuthorsV2()

        val myMegaAuthorList = getAllAuthors()

        myMegaList = myMegaList.plus(AuthorList(myMegaAuthorList))

        myMegaList = myMegaList.plus(myMegaAuthorList)

        return myMegaList
    }

    override suspend fun getAllAuthors(): List<Author> {
        return appDao.getAllAuthors().map { it.toDomain() }
    }

    override suspend fun getAllBooksWithAuthors(): List<Book> {
        return appDao.getAllBooks().map { it.toDomain() }
    }

    override suspend fun getAllBooksWithAuthorsV2(): List<Book> {
        return appDao.getAllBooksV2().map { it.toDomain() }
    }

    override suspend fun insertAuthors(authors: List<Author>) {
        appDao.insertAllAuthors(authors.map { it.toEntity() })
    }

    override suspend fun insertBooks(books: List<Book>) {
        appDao.insertAllBooks(books = (books.map { it.toEntity() }.toTypedArray()))
    }

    override suspend fun clearBooks() {
        appDao.getAllBooks()
    }

    override suspend fun clearAuthors() {
        appDao.deleteAllAuthors()
    }

    override suspend fun deleteBook(book: Book) {
        appDao.deleteBook(book.toEntity())
    }

    override suspend fun deleteAuthor(author: Author) {
        appDao.deleteAuthor(author.toEntity())
    }

    override suspend fun updateBook(book: Book) {
        appDao.updateBook(book.toEntity())
    }
}