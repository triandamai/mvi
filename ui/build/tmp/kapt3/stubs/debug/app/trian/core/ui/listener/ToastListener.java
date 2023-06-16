package app.trian.core.ui.listener;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J5\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0007\"\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u0005H&\u00a2\u0006\u0002\u0010\nJ\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u0005H&\u00a8\u0006\u000b"}, d2 = {"Lapp/trian/core/ui/listener/ToastListener;", "", "showToast", "", "message", "", "params", "", "", "length", "(I[Ljava/lang/String;I)V", "core-ui_debug"})
public abstract interface ToastListener {
    
    public abstract void showToast(@org.jetbrains.annotations.NotNull
    java.lang.String message, int length);
    
    public abstract void showToast(@androidx.annotation.RawRes
    int message, @org.jetbrains.annotations.NotNull
    java.lang.String[] params, int length);
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 3)
    public final class DefaultImpls {
    }
}