package ua.edu.cdu.boris.newnav2025.model

data class Author(
    override val id: Int = 0,
    var name: String,
    val birthday: String
) : IListable
