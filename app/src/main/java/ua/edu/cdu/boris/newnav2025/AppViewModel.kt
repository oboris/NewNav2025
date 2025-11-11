package ua.edu.cdu.boris.newnav2025

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ua.edu.cdu.boris.newnav2025.model.Author
import ua.edu.cdu.boris.newnav2025.model.IListable
import ua.edu.cdu.boris.newnav2025.repository.AppRepository
import ua.edu.cdu.boris.newnav2025.repository.AppRepositoryImpl

class AppViewModel(private val repo : AppRepository = AppRepositoryImpl()) : ViewModel() {

    private val _author: MutableLiveData<Author> =
        MutableLiveData(Author("Author Author", "0/0/0"))
    val author: LiveData<Author> = _author

    fun updateAuthor() {
        val auth = _author.value
        auth?.name = "New Author"
        _author.postValue(auth!!)
    }

    private val _isShowToast =
        MutableSharedFlow<Boolean>()
    val isShowToast = _isShowToast.asSharedFlow()

    fun showToast() {
        viewModelScope.launch {
            _isShowToast.emit(true)
        }
    }

    private val _myMegaList: MutableLiveData<List<IListable>> =
        MutableLiveData<List<IListable>>(emptyList())
    val myMegaList: LiveData<List<IListable>> = _myMegaList

    fun getAllData() {
        _myMegaList.postValue(repo.getAllData())
    }
}