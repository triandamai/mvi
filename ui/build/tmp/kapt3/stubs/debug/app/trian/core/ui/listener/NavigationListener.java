package app.trian.core.ui.listener;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J)\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0007\"\u00020\u0005H&\u00a2\u0006\u0002\u0010\bJ)\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0007\"\u00020\u0005H&\u00a2\u0006\u0002\u0010\bJ)\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0007\"\u00020\u0005H&\u00a2\u0006\u0002\u0010\bJ)\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0007\"\u00020\u0005H&\u00a2\u0006\u0002\u0010\bJ\b\u0010\f\u001a\u00020\u0003H&\u00a8\u0006\r"}, d2 = {"Lapp/trian/core/ui/listener/NavigationListener;", "", "navigate", "", "route", "", "params", "", "(Ljava/lang/String;[Ljava/lang/String;)V", "navigateAndReplace", "navigateBackAndClose", "navigateSingleTop", "navigateUp", "core-ui_debug"})
public abstract interface NavigationListener {
    
    public abstract void navigateUp();
    
    public abstract void navigate(@org.jetbrains.annotations.NotNull
    java.lang.String route, @org.jetbrains.annotations.NotNull
    java.lang.String... params);
    
    public abstract void navigateSingleTop(@org.jetbrains.annotations.NotNull
    java.lang.String route, @org.jetbrains.annotations.NotNull
    java.lang.String... params);
    
    public abstract void navigateAndReplace(@org.jetbrains.annotations.NotNull
    java.lang.String route, @org.jetbrains.annotations.NotNull
    java.lang.String... params);
    
    public abstract void navigateBackAndClose(@org.jetbrains.annotations.NotNull
    java.lang.String route, @org.jetbrains.annotations.NotNull
    java.lang.String... params);
}