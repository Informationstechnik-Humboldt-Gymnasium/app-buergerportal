package de.humboldtgym.maiskolbistan.buergerportal.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Announcement(
    val id: String = "",
    val title: String = "",
    val body: String = "",
    val date: String = "",
    val priority: String = "normal"
)
