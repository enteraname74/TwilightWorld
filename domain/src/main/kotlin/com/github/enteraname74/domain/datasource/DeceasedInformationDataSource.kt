package com.github.enteraname74.domain.datasource

import com.github.enteraname74.domain.model.PaginatedResult
import com.github.enteraname74.domain.model.Person
import com.github.enteraname74.domain.model.SearchQuery

/**
 * Data source for managing deceased people information.
 */
interface DeceasedInformationDataSource {

    /**
     * Retrieve a list of person from a search.
     *
     * @param searchQuery the search parameters used for the search.
     * @return a list of Person matching the search query.
     */
    suspend fun getAllFromSearch(searchQuery: SearchQuery): PaginatedResult<List<Person>>
}