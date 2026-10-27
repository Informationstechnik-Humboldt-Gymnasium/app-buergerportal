package de.humboldtgym.maiskolbistan.buergerportal.data.model

import kotlinx.serialization.Serializable

@Serializable
data class KvEntry(
    val key: String = "",
    val value: String? = null
)
