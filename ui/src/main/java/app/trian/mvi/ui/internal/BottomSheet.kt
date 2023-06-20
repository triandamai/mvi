package app.trian.mvi.ui.internal

import androidx.compose.material.BottomSheetState
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.SwipeableDefaults
import app.trian.mvi.ui.internal.listener.BottomSheetChangeListener
import app.trian.mvi.ui.internal.listener.BottomSheetListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class BottomSheet(
    private val coroutineScope: CoroutineScope
) : BottomSheetListener {

    private var _modalBottomSheetState: ModalBottomSheetState?=null
    private var _listener: BottomSheetChangeListener? = null

    val state get() = _modalBottomSheetState
    fun onStateChangedListener(listener: BottomSheetChangeListener) {
        _listener = listener
    }

    fun changeState(value: ModalBottomSheetValue): Boolean {
        return _listener?.onChange(value) ?: false
    }

    fun setState(modalBottomSheetState: ModalBottomSheetState){
        _modalBottomSheetState = modalBottomSheetState
    }

    override fun show() {
        coroutineScope.launch { state?.show() }
    }

    override fun hide() {
        coroutineScope.launch { state?.hide() }
    }

}