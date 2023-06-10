/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.resepku.feature.dashboard

import app.trian.resepku.base.BaseViewModelData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
) : BaseViewModelData<DashboardState, DashboardDataState, DashboardEvent>(
    DashboardState(),
    DashboardDataState()
) {
    init {
        handleActions()
    }

    override fun handleActions() = onEvent {}

}