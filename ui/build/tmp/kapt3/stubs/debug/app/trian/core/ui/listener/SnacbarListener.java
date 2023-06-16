package app.trian.core.ui.listener;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J)\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0007\"\u00020\bH&\u00a2\u0006\u0002\u0010\tJ1\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0007\"\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH&\u00a2\u0006\u0002\u0010\fJ\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\bH&J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000bH&\u00a8\u0006\u000f"}, d2 = {"Lapp/trian/core/ui/listener/SnacbarListener;", "", "showSnackbar", "", "id", "", "params", "", "", "(I[Ljava/lang/String;)V", "snackbarDuration", "Landroidx/compose/material3/SnackbarDuration;", "(I[Ljava/lang/String;Landroidx/compose/material3/SnackbarDuration;)V", "message", "duration", "core-ui_debug"})
public abstract interface SnacbarListener {
    
    public abstract void showSnackbar(@org.jetbrains.annotations.NotNull
    java.lang.String message);
    
    public abstract void showSnackbar(@org.jetbrains.annotations.NotNull
    java.lang.String message, @org.jetbrains.annotations.NotNull
    androidx.compose.material3.SnackbarDuration duration);
    
    public abstract void showSnackbar(int id, @org.jetbrains.annotations.NotNull
    java.lang.String... params);
    
    public abstract void showSnackbar(int id, @org.jetbrains.annotations.NotNull
    java.lang.String[] params, @org.jetbrains.annotations.NotNull
    androidx.compose.material3.SnackbarDuration snackbarDuration);
}