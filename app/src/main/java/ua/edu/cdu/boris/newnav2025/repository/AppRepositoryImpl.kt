package ua.edu.cdu.boris.newnav2025.repository

import ua.edu.cdu.boris.newnav2025.dao.AppDao
import ua.edu.cdu.boris.newnav2025.entity.AuthorEntity
import ua.edu.cdu.boris.newnav2025.model.Author
import ua.edu.cdu.boris.newnav2025.model.AuthorList
import ua.edu.cdu.boris.newnav2025.model.Book
import ua.edu.cdu.boris.newnav2025.model.IListable
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

    override fun getAllData(): List<IListable> {

        val myMegaAuthorList = loadAuthors()

        var myMegaList: List<IListable> = loadBooks()

        myMegaList = myMegaList.plus(AuthorList(myMegaAuthorList))

        myMegaList = myMegaList.plus(myMegaAuthorList)

//        var myMegaList = listOf(
//            Book(
//                title = "Book 1",
//                pages = 100,
//                author = myMegaAuthorList[0]
//            ),
//
//            myMegaAuthorList[0],
//
//            Book(
//                title = "Book 2",
//                pages = 110,
//                author = myMegaAuthorList[0]
//            ),
//
//            Book(
//                title = "Book 3",
//                pages = 10,
//                author = myMegaAuthorList[1]
//            ),
//
//            myMegaAuthorList[1],
//
//            Book(
//                title = "Book 4",
//                pages = 300,
//                author = myMegaAuthorList[0]
//            ),
//
//            Book(
//                title = "Book 5",
//                pages = 50,
//                author = myMegaAuthorList[1]
//            ),
//
//            Book(
//                title = "Book 6",
//                pages = 100,
//                author = myMegaAuthorList[2]
//            ),
//
//            myMegaAuthorList[2],
//
//            Book(
//                title = "Book 7",
//                pages = 100,
//                author = myMegaAuthorList[0]
//            )
//        )
//
//        val aList = myMegaList.filter { (it is Author) }
//        var authorList: List<Author> = emptyList()
//        for (it in aList) {
//            authorList = authorList.plus(it as Author)
//        }
//        myMegaList = myMegaList.plus(AuthorList(authorList))

        return myMegaList
    }

    override fun insertAuthors(authors: List<Author>) {
        appDao.insertAllAuthors(authors.map { it.toEntity() })
    }

    override fun insertBooks(books: List<Book>) {
        appDao.insertAllBooks(*(books.map { it.toEntity() }.toTypedArray()))
    }
}