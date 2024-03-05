package com.github.enteraname74.domain.model

/**
 * Information about the birth date and location of someone.
 */
data class DeathInformation(
    val date: String,
    val age: Int,
    val location: Location
)
