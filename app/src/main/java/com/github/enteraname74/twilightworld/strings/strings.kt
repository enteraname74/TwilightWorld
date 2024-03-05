package com.github.enteraname74.twilightworld.strings

import androidx.compose.ui.text.intl.Locale

/**
 * The application's strings, adapted from the current language used on the device.
 */
val appStrings = when(Locale.current.language) {
    "fr" -> FrStrings
    else -> EnStrings
}

/**
 * Strings used for the applications.
 */
interface ApplicationStrings {
    val appName: String get() = "Twilight World"

    val fetchingPersons: String
    val firstname: String
    val lastname: String
    val nothingFound: String
    val birthInformation: String
    val deathInformation: String
}