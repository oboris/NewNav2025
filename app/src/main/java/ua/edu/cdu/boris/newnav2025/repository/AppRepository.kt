package ua.edu.cdu.boris.newnav2025.repository

import ua.edu.cdu.boris.newnav2025.model.IListable

interface AppRepository {
    fun getAllData(): List<IListable>
}