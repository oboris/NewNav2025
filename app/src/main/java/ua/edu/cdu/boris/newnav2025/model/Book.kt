package ua.edu.cdu.boris.newnav2025.model

data class Book(
    val id: Int = 0,
    val title: String,
    val pages: Int,
    val author: Author
) : IListable
