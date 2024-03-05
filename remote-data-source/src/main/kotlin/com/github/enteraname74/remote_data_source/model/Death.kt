package com.github.enteraname74.remote_data_source.model

import com.github.enteraname74.domain.model.DeathInformation
import kotlinx.serialization.Serializable
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Information about the death of someone.
 */
@Serializable
internal data class Death(
    val date: String = "19700101",
    val age: Int = 0,
    val location: City = City()
)

/**
 * Converts a Death instance to a DeathInformation.
 */
internal fun Death.toDeathInformation(): DeathInformation {
    val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")

    return DeathInformation(
        date = date,
        age = age,
        location = location.toLocation()
    )
}
