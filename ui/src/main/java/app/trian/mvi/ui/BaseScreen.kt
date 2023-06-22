/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.mvi.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun BaseScreen(
    modalBottomSheetState: ModalBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden),
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    topAppBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    bottomSheet: @Composable () -> Unit = { },
    content: @Composable () -> Unit = { }
) {
    ModalBottomSheetLayout(
        sheetContent = {
            Column(
                Modifier
                    .defaultMinSize(
                        minHeight = 50.dp
                    )
                    .wrapContentHeight()
            ) {
                bottomSheet()
            }
        },
        sheetState = modalBottomSheetState,
        sheetBackgroundColor = MaterialTheme.colorScheme.surface,
        sheetShape = RoundedCornerShape(
            topStart = 10.dp,
            topEnd = 10.dp
        )
    ) {
        Scaffold(
            topBar = topAppBar,
            bottomBar = bottomBar,
            snackbarHost = {},
            containerColor = backgroundColor,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ) {
            Column(
                modifier = Modifier.padding(it)
            ) {
                content()
            }
        }
    }
}