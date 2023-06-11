/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.resepku.feature.dashboard.home

import app.trian.core.ui.viewModel.BaseViewModelData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
) : BaseViewModelData<HomeState, HomeDataState, HomeEvent>(
    HomeState(),
    HomeDataState()
) {
    init {
        handleActions()
    }

    private fun calculatePager(page: Int) = async {
        val percentage = when (page) {
            0 -> 0.1f
            1 -> 0.2f
            2 -> 0.5f
            3 -> 0.7f
            4 -> 1f
            else -> 0.1f
        }
        commit { copy(percentage = percentage) }
    }

    override fun handleActions() = onEvent {
        when (it) {
            is HomeEvent.PagerChanges -> calculatePager(it.page)
        }
    }

}