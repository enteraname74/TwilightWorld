package com.github.enteraname74.twilightworld.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.enteraname74.domain.datasource.DeceasedInformationDataSource
import com.github.enteraname74.domain.model.SearchKeys
import com.github.enteraname74.domain.model.SearchQuery
import com.github.enteraname74.twilightworld.event.MainScreenEvent
import com.github.enteraname74.twilightworld.model.FetchingState
import com.github.enteraname74.twilightworld.state.MainScreenState
import com.github.enteraname74.twilightworld.strings.appStrings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * View model for the main screen.
 */
class MainScreenViewModel(
    private val dataSource: DeceasedInformationDataSource
) : ViewModel() {
    private val _state = MutableStateFlow(MainScreenState())
    val state = _state.asStateFlow()

    /**
     * Handles events from the main screen.
     */
    fun onEvent(event: MainScreenEvent) {
        viewModelScope.launch {
            when(event) {
                is MainScreenEvent.SetFirstname -> setFirstname(firstname = event.firstname)
                is MainScreenEvent.SetLastname -> setLastname(lastname = event.lastname)
                is MainScreenEvent.SetPage -> TODO()
            }
        }
    }

    /**
     * Set the firstname used for the search.
     * @param firstname the new firstname.
     */
    private suspend fun setFirstname(firstname: String) {
        _state.update {
            it.copy(
                firstname = firstname
            )
        }
        updateScreenInformation()
    }

    /**
     * Set the lastname used for the search.
     * @param lastname the new lastname.
     */
    private suspend fun setLastname(lastname: String) {
        _state.update {
            it.copy(
                lastname = lastname
            )
        }
        updateScreenInformation()
    }

    /**
     * Check if we can do a search with our current information.
     *
     * @return true if we can do a search, false if not.
     */
    fun canDoSearch(): Boolean {
        return _state.value.lastname.isNotBlank() || _state.value.firstname.isNotBlank()
    }

    /**
     * Retrieve the max number of pages.
     */
    fun getMaxPages(): Int = _state.value.total / _state.value.pageSize;

    /**
     * Update data information shown on the main screen.
     */
    private suspend fun updateScreenInformation() {
        val searchQuery = SearchQuery(
            parameters = mapOf(
                Pair(SearchKeys.FIRSTNAME, _state.value.firstname),
                Pair(SearchKeys.LASTNAME, _state.value.lastname),
            ),
            page = _state.value.page,
            pageSize = _state.value.pageSize
        )

        if (canDoSearch()) {
            _state.update {
                it.copy(
                    allPersonsList = FetchingState.Loading(message = appStrings.fetchingPersons)
                )
            }

            val paginatedResult = dataSource.getAllFromSearch(searchQuery)

            _state.update {
                it.copy(
                    allPersonsList = FetchingState.Success(paginatedResult.data),
                )
            }
        }
    }

    /**
     * Set the current page and update screen information.
     */
    private suspend fun setCurrentPage(page: Int) {
        _state.update {
            it.copy(
                page = page
            )
        }
        updateScreenInformation()
    }
}