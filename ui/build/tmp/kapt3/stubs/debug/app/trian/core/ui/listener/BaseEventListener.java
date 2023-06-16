package app.trian.core.ui.listener;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006J\u000e\u0010\n\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\bJ\u000e\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0012R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lapp/trian/core/ui/listener/BaseEventListener;", "", "()V", "appEvent", "Lapp/trian/core/ui/listener/AppStateEventListener;", "bottomSheetChangeListener", "Lapp/trian/core/ui/listener/BottomSheetChangeListener;", "addOnBottomSheetChangeListener", "", "listener", "addOnEventListener", "changeBottomSheet", "", "value", "Landroidx/compose/material/ModalBottomSheetValue;", "clear", "sendEvent", "eventName", "", "core-ui_debug"})
public abstract class BaseEventListener {
    private app.trian.core.ui.listener.AppStateEventListener appEvent;
    private app.trian.core.ui.listener.BottomSheetChangeListener bottomSheetChangeListener;
    
    public BaseEventListener() {
        super();
    }
    
    public final void addOnEventListener(@org.jetbrains.annotations.NotNull
    app.trian.core.ui.listener.AppStateEventListener listener) {
    }
    
    public final void sendEvent(@org.jetbrains.annotations.NotNull
    java.lang.String eventName) {
    }
    
    public final void addOnBottomSheetChangeListener(@org.jetbrains.annotations.NotNull
    app.trian.core.ui.listener.BottomSheetChangeListener listener) {
    }
    
    public final boolean changeBottomSheet(@org.jetbrains.annotations.NotNull
    androidx.compose.material.ModalBottomSheetValue value) {
        return false;
    }
    
    public final void clear() {
    }
}