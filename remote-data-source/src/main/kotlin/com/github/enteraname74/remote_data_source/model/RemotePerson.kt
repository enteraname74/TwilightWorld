package com.github.enteraname74.remote_data_source.model

import com.github.enteraname74.domain.model.Person
import kotlinx.serialization.Serializable

/**
 * Represent a Person from the remote source.
 */
@Serializable
internal data class RemotePerson(
    val id: String = "",
    val name: PersonName = PersonName(),
    val sex: String,
    val birth: Birth,
    val death: Death
)

/**
 * Converts a RemotePerson to a Person.
 */
internal fun RemotePerson.toPerson() = Person(
    id = id,
    firstNames = name.first,
    lastName = name.last,
    sex = sex,
    birth = birth.toBirthInformation(),
    death = death.toDeathInformation()
)
