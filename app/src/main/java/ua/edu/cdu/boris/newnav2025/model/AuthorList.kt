package ua.edu.cdu.boris.newnav2025.model

data class AuthorList(
    val authorList: List<Author>, override val id: Int = 0
) : IListable
