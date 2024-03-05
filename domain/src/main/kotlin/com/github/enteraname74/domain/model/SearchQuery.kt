package com.github.enteraname74.domain.model

/**
 * Parameters used for a search.
 */
data class SearchQuery(
    val parameters: Map<SearchKeys, Any>,
    val page: Int,
    val pageSize: Int
)
