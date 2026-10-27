package de.humboldtgym.maiskolbistan.buergerportal.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    val id: String = "",
    val citizen_id: String? = null,
    val level: Int? = 0,
    val is_admin: Boolean? = false,
    val email: String = "",
    val displayName: String? = null
)
