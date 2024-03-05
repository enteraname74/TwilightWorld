package com.github.enteraname74.domain.model

/**
 * Paginated result of a query.
 *
 * @param page the current page.
 * @param T the data returned.
 */
data class PaginatedResult<T>(
    val page: Int,
    val total: Int,
    val data: T
)
