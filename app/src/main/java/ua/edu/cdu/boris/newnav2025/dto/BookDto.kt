package ua.edu.cdu.boris.newnav2025.dto

import retrofit2.http.Field

data class BookDto(
    val id: Int,
    val title: String,
    val pages: Int,
    val author: AuthorDto
)
