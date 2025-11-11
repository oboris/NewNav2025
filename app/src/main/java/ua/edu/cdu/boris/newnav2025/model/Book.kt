package ua.edu.cdu.boris.newnav2025.model

data class Book(
    val title: String,
    val pages: Int,
    val author: String
) : IListable
