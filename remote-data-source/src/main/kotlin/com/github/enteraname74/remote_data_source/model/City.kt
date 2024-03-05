package com.github.enteraname74.remote_data_source.model

import com.github.enteraname74.domain.model.Location
import kotlinx.serialization.Serializable

/**
 * Information about a city.
 */
@Serializable
internal data class City(
//    val city: List<String> = emptyList(),
    val codePostal: List<String> = emptyList(),
    val departmentCode: String = "",
)

/**
 * Convert a City to a Location.
 */
internal fun City.toLocation() = Location(
    city = "",
    postalCode = codePostal.getOrElse(0) { "" },
    departmentCode = departmentCode
)
