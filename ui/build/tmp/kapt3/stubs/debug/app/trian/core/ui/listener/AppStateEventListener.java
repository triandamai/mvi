package app.trian.core.ui.listener;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u00e6\u0080\u0001\u0018\u00002\u00020\u0001J)\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0007\"\u00020\u0005H&\u00a2\u0006\u0002\u0010\b\u00a8\u0006\t"}, d2 = {"Lapp/trian/core/ui/listener/AppStateEventListener;", "", "onEvent", "", "eventName", "", "params", "", "(Ljava/lang/String;[Ljava/lang/String;)V", "core-ui_debug"})
public abstract interface AppStateEventListener {
    
    public abstract void onEvent(@org.jetbrains.annotations.NotNull
    java.lang.String eventName, @org.jetbrains.annotations.NotNull
    java.lang.String... params);
}