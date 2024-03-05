package com.github.enteraname74.remote_data_source.model

import com.github.enteraname74.domain.model.BirthInformation
import kotlinx.serialization.Serializable
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Information about the birth of someone.
 */
@Serializable
internal data class Birth(
    val date: String = "19700101",
    val location: City = City()
)

/**
 * Converts a Birth instance to a BirthInformation.
 */
internal fun Birth.toBirthInformation(): BirthInformation {
    return BirthInformation(
        date = date,
        location = location.toLocation()
    )
}
