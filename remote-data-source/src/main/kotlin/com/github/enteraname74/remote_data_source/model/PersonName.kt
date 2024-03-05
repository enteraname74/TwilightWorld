package com.github.enteraname74.remote_data_source.model

import kotlinx.serialization.Serializable

/**
 * Information about the names of a Person.
 */
@Serializable
internal data class PersonName(
    val first: List<String> = emptyList(),
    val last: String = ""
)
