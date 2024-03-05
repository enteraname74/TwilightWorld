package com.github.enteraname74.domain.model

/**
 * All information concerning someone.
 */
data class Person(
    val id: String,
    val firstNames: List<String>,
    val lastName: String,
    val sex: String,
    val birth: BirthInformation,
    val death: DeathInformation
)
