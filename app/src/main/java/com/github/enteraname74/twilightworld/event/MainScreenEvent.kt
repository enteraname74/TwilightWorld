package com.github.enteraname74.twilightworld.event

/**
 * Events for the main screen page
 */
sealed interface MainScreenEvent {
    /**
     * Define the firstname shown on the screen.
     */
    data class SetFirstname(val firstname: String) : MainScreenEvent

    /**
     * Define the lastname shown on the screen.
     */
    data class SetLastname(val lastname: String): MainScreenEvent

    /**
     * Define the current page on the main screen.
     */
    data class SetPage(val page: Int): MainScreenEvent
}