package ua.edu.cdu.boris.newnav2025

import android.app.Application
import ua.edu.cdu.boris.newnav2025.bd.AppDb
import ua.edu.cdu.boris.newnav2025.repository.AppRepositoryImpl

class MyApp  : Application() {
    private val appDb by lazy { AppDb.getDatabase(this) }
    val appRepository by lazy { AppRepositoryImpl(appDb.appDao()) }
}