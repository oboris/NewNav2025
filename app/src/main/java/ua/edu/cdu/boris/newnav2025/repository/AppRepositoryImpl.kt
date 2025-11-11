package ua.edu.cdu.boris.newnav2025.repository

import ua.edu.cdu.boris.newnav2025.model.Author
import ua.edu.cdu.boris.newnav2025.model.AuthorList
import ua.edu.cdu.boris.newnav2025.model.Book
import ua.edu.cdu.boris.newnav2025.model.IListable

class AppRepositoryImpl : AppRepository {
    override fun getAllData(): List<IListable> {
        var myMegaList = listOf(
            Book(
                title = "Book 1",
                pages = 100,
                author = "Author 1"
            ),

            Author(
                name = "Author 1",
                birthday = "01/01/2000"
            ),

            Book(
                title = "Book 2",
                pages = 110,
                author = "Author 1"
            ),

            Book(
                title = "Book 3",
                pages = 10,
                author = "Author 2"
            ),

            Author(
                name = "Author 2",
                birthday = "01/01/2010"
            ),

            Book(
                title = "Book 4",
                pages = 300,
                author = "Author 1"
            ),

            Book(
                title = "Book 5",
                pages = 50,
                author = "Author 2"
            ),

            Book(
                title = "Book 6",
                pages = 100,
                author = "Author 3"
            ),

            Author(
                name = "Author 3",
                birthday = "02/02/2020"
            ),

            Book(
                title = "Book 7",
                pages = 100,
                author = "Author 1"
            )
        )

        val aList = myMegaList.filter { (it is Author) }
        var authorList: List<Author> = emptyList()
        for (it in aList) {
            authorList = authorList.plus(it as Author)
        }
        myMegaList = myMegaList.plus(AuthorList(authorList))

        return myMegaList
    }
}