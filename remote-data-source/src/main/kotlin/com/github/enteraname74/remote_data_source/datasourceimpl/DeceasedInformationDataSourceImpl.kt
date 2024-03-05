package com.github.enteraname74.remote_data_source.datasourceimpl

import com.github.enteraname74.domain.datasource.DeceasedInformationDataSource
import com.github.enteraname74.domain.model.PaginatedResult
import com.github.enteraname74.domain.model.Person
import com.github.enteraname74.domain.model.SearchQuery
import com.github.enteraname74.remote_data_source.model.AppHttpClient
import com.github.enteraname74.remote_data_source.model.RemotePaginatedSearchResponse
import com.github.enteraname74.remote_data_source.model.forRequest
import com.github.enteraname74.remote_data_source.model.toPaginatedResponse
import com.github.enteraname74.remote_data_source.utils.Routes

/**
 * Implementation of the DeceasedInformationDataSource interface using matchid api.
 */
class DeceasedInformationDataSourceImpl: DeceasedInformationDataSource {
    private val client = AppHttpClient()

    override suspend fun getAllFromSearch(searchQuery: SearchQuery): PaginatedResult<List<Person>> {
        println(searchQuery)
        val result: RemotePaginatedSearchResponse? = client.post(
            url = Routes.SEARCH,
            body = searchQuery.forRequest()
        )
        println(result)

        return result?.toPaginatedResponse() ?: PaginatedResult(
            page = 1,
            total = 1,
            data = emptyList()
        )
    }
}