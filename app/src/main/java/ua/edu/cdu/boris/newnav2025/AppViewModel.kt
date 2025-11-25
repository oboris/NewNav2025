package ua.edu.cdu.boris.newnav2025

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ua.edu.cdu.boris.newnav2025.model.Author
import ua.edu.cdu.boris.newnav2025.model.Book
import ua.edu.cdu.boris.newnav2025.model.IListable
import ua.edu.cdu.boris.newnav2025.repository.AppRepository


class AppViewModel(application: Application) : AndroidViewModel(application) {

    private val repo : AppRepository = (application as MyApp).appRepository

    private val _author: MutableLiveData<Author> =
        MutableLiveData(Author(name = "Author Author", birthday = "0/0/0"))
    val author: LiveData<Author> = _author

    fun updateAuthor() {
        val auth = _author.value
        auth?.name = "New Author"
        _author.postValue(auth!!)
    }

    private val _isShowToastLiveData = MutableLiveData(false)
    val isShowToastLiveData = _isShowToastLiveData

    fun showToastLiveData() {
        _isShowToastLiveData.postValue(true)
    }

    private val _isShowToastState = mutableStateOf(false)
    val isShowToastState = _isShowToastState

    fun showToastState() {
        _isShowToastState.value = true
    }

    private val _isShowToastSharedFlow = MutableSharedFlow<Boolean>()
    val isShowToastSharedFlow = _isShowToastSharedFlow.asSharedFlow()

    fun showToastSharedFlow() {
        viewModelScope.launch {
            _isShowToastSharedFlow.emit(true)
        }
    }

    private val _myMegaList: MutableLiveData<List<IListable>> =
        MutableLiveData<List<IListable>>(emptyList())
    val myMegaList: LiveData<List<IListable>> = _myMegaList

    suspend fun getAllData() {
        _myMegaList.postValue(repo.getAllData())
    }

    var myMegaAuthorList = listOf(
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

    fun loadAllDataToDb() {
        viewModelScope.launch {
            repo.insertAuthors(myMegaAuthorList)
            myMegaAuthorList = repo.getAllAuthors()

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
            repo.insertBooks(myMegaBookList)

            getAllData()
        }
    }

    fun clearAllDataFromDb() {
        viewModelScope.launch {
            repo.clearBooks()
            repo.clearAuthors()
            getAllData()
        }
    }

    fun deleteBookFromDb(book: Book){
        viewModelScope.launch {
            repo.deleteBook(book)
            getAllData()
        }
    }

    fun deleteAuthorFromDb(author: Author){
        viewModelScope.launch {
            repo.deleteAuthor(author)
            getAllData()
        }
    }

    fun changePagesOfBook(book: Book, delta: Int){
        val book1 = book.copy(pages = book.pages + delta)
        viewModelScope.launch {
            repo.updateBook(book1)
            getAllData()
        }
    }
}
