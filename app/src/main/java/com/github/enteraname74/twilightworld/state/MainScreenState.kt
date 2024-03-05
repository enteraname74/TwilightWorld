package com.github.enteraname74.twilightworld.state

import com.github.enteraname74.domain.model.Person
import com.github.enteraname74.twilightworld.model.FetchingState
import com.github.enteraname74.twilightworld.strings.appStrings

/**
 * UI state for the main screen.
 */
data class MainScreenState(
    val firstname: String = "",
    val lastname: String = "",
    val page: Int = 1,
    val total: Int = 1,
    val pageSize: Int = 25,
    val allPersonsList: FetchingState<List<Person>> = FetchingState.Loading(message = appStrings.fetchingPersons)
)
