package com.github.enteraname74.remote_data_source.model

import com.github.enteraname74.domain.model.SearchKeys
import com.github.enteraname74.domain.model.SearchQuery
import kotlinx.serialization.Serializable

/**
 * Search body request used for a search request.
 */
@Serializable
internal data class SearchBodyRequest(
    val firstName: String,
    val lastName: String,
    val size: Int,
    val page: Int
)

/**
 * Maje a SearchQuery usable for a request.
 */
internal fun SearchQuery.forRequest() = SearchBodyRequest(
    firstName = parameters[SearchKeys.FIRSTNAME]?.toString() ?: "",
    lastName = parameters[SearchKeys.LASTNAME]?.toString() ?: "",
    size = pageSize,
    page = page
)
