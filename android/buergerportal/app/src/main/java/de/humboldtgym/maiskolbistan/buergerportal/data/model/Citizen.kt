package de.humboldtgym.maiskolbistan.buergerportal.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Citizen(
    val id: String = "",
    val name: String = "",
    val email: String? = null,
    val loginEmail: String? = null,
    val status: String = "",
    val role: String = "",
    val since: String? = null,
    val other: String? = null,
    val ministries: List<String> = emptyList(),
    val locked: Boolean = false
)
