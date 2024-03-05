package com.github.enteraname74.remote_data_source

import com.github.enteraname74.domain.datasource.DeceasedInformationDataSource
import com.github.enteraname74.remote_data_source.datasourceimpl.DeceasedInformationDataSourceImpl
import org.koin.dsl.module

/**
 * Dependency Module for the remote data source.
 */
val remoteDataSourceModule = module {
    single<DeceasedInformationDataSource> { DeceasedInformationDataSourceImpl() }
}