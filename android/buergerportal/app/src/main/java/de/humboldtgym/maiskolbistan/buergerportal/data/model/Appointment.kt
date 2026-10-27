package de.humboldtgym.maiskolbistan.buergerportal.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Appointment(
    val id: String = "",
    val ref: String = "",
    val title: String = "",
    val office: String = "",
    val date: String = "",
    val time: String = "",
    val note: String = "",
    val citizenId: String? = null,
    val status: String = ""
)
