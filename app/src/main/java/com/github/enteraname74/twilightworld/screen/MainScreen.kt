package com.github.enteraname74.twilightworld.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.github.enteraname74.domain.model.BirthInformation
import com.github.enteraname74.domain.model.DeathInformation
import com.github.enteraname74.domain.model.Person
import com.github.enteraname74.twilightworld.Constants
import com.github.enteraname74.twilightworld.composable.StateIndication
import com.github.enteraname74.twilightworld.event.MainScreenEvent
import com.github.enteraname74.twilightworld.model.FetchingState
import com.github.enteraname74.twilightworld.strings.appStrings
import com.github.enteraname74.twilightworld.viewmodel.MainScreenViewModel

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .fillMaxSize()

    ) {
        val state by viewModel.state.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = Constants.Spacing.medium),
            verticalArrangement = Arrangement.spacedBy(Constants.Spacing.medium)
        ) {
            SearchSection(
                lastname = state.lastname,
                firstname = state.firstname,
                onLastnameChange = {
                    viewModel.onEvent(MainScreenEvent.SetLastname(it))
                },
                onFirstnameChange = {
                    viewModel.onEvent(MainScreenEvent.SetFirstname(it))
                }
            )

            if (viewModel.canDoSearch()) {
                when (state.allPersonsList) {
                    is FetchingState.Success -> {
                        if ((state.allPersonsList as FetchingState.Success<List<Person>>).data.isEmpty()) StateIndication(
                            message = appStrings.nothingFound
                        )
                        else MainList(persons = (state.allPersonsList as FetchingState.Success<List<Person>>).data)
                    }

                    else -> StateIndication(message = appStrings.fetchingPersons)
                }
            }
        }
    }
}

@Composable
private fun PersonElement(person: Person) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = Constants.Spacing.small),
            verticalArrangement = Arrangement.spacedBy(Constants.Spacing.small)
        ) {
            var firstNames = ""
            person.firstNames.forEach {
                firstNames += "$it "
            }
            Text(
                text = "$firstNames${person.lastName}",
                style = MaterialTheme.typography.bodyLarge
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                BirthInformationComposable(birthInformation = person.birth)
                DeathInformationComposable(deathInformation = person.death)
            }
        }
    }
}

@Composable
private fun BirthInformationComposable(
    birthInformation: BirthInformation
) {
    Column {
        Text(
            text = appStrings.birthInformation,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "${birthInformation.date}",
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = birthInformation.location.city,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
private fun DeathInformationComposable(
    deathInformation: DeathInformation
) {
    Column {
        Text(
            text = appStrings.deathInformation,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "${deathInformation.date}",
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = deathInformation.location.city,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
private fun MainList(
    persons: List<Person>
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(Constants.Spacing.medium)
    ) {
        items(persons) {
            PersonElement(person = it)
        }
    }
}

/**
 * Search section of the main screen where the user write information to be used by a search.
 */
@Composable
private fun SearchSection(
    lastname: String,
    firstname: String,
    onLastnameChange: (String) -> Unit,
    onFirstnameChange: (String) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(Constants.Spacing.medium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = firstname,
            onValueChange = onFirstnameChange,
            label = {
                Text(text = appStrings.firstname)
            }
        )
        OutlinedTextField(
            value = lastname,
            onValueChange = onLastnameChange,
            label = {
                Text(text = appStrings.lastname)
            }
        )
    }
}