package com.github.enteraname74.twilightworld

import com.github.enteraname74.twilightworld.viewmodel.MainScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Dependency module for the application.
 */
val appModule = module {
    viewModel {
        MainScreenViewModel(
            dataSource = get()
        )
    }
}