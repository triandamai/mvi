package app.trian.mvi.ui.internal

import androidx.compose.material.ModalBottomSheetState
import app.trian.mvi.ui.internal.listener.BottomSheetListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class BottomSheet(
    private val bottomSheet: ModalBottomSheetState,
    private val coroutineScope: CoroutineScope
):BottomSheetListener {
    val state get() = bottomSheet
    override fun show() {
        coroutineScope.launch {  bottomSheet.show()}
    }

    override fun hide() {
        coroutineScope.launch {  bottomSheet.hide()}
    }

}