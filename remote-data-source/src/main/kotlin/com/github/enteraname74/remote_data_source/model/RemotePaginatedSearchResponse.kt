package com.github.enteraname74.remote_data_source.model

import com.github.enteraname74.domain.model.PaginatedResult
import com.github.enteraname74.domain.model.Person
import kotlinx.serialization.Serializable

/**
 * Search response as a paginated result from a query.
 */
@Serializable
internal data class RemotePaginatedSearchResponse(
    val response: SearchResponseData = SearchResponseData()
)

/**
 * Main search response body.
 */
@Serializable
internal data class SearchResponseData(
    val total: Int = 1,
    val page: Int = 1,
    val persons: List<RemotePerson> = emptyList()
)

/**
 * Converts a RemotePaginatedSearchResponse to a PaginatedResponse.
 */
internal fun RemotePaginatedSearchResponse.toPaginatedResponse() = PaginatedResult<List<Person>>(
    page = response.page,
    total = response.total,
    data = response.persons.map { it.toPerson() }
)

