package app.trian.core.ui;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0017\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003BA\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00028\u0000\u0012\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\t0\b\u0012\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\t0\b\u00a2\u0006\u0002\u0010\u000bJ\u000e\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\u0019J\u001f\u0010\u0007\u001a\u00020\t2\u0017\u0010\u001a\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00000\b\u00a2\u0006\u0002\b\u001bJ\u0013\u0010\u001c\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00028\u0001\u00a2\u0006\u0002\u0010\u001eJ\u0006\u0010\u001f\u001a\u00020\tJ\u0006\u0010 \u001a\u00020\tJ\u0006\u0010!\u001a\u00020\tJ\u000e\u0010\"\u001a\u00020\t2\u0006\u0010#\u001a\u00020$J\'\u0010\"\u001a\u00020\t2\u0006\u0010#\u001a\u00020$2\u0012\u0010%\u001a\n\u0012\u0006\b\u0001\u0012\u00020\'0&\"\u00020\'\u00a2\u0006\u0002\u0010(J\u000e\u0010\"\u001a\u00020\t2\u0006\u0010#\u001a\u00020\'R\u0013\u0010\f\u001a\u0004\u0018\u00010\r8F\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0012\u001a\u00020\u00058F\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0011R\u0013\u0010\u0006\u001a\u00028\u0000\u00a2\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0014\u0010\u0015\u00a8\u0006)"}, d2 = {"Lapp/trian/core/ui/UIListener;", "State", "Event", "", "controller", "Lapp/trian/core/ui/UIController;", "state", "commit", "Lkotlin/Function1;", "", "dispatcher", "(Lapp/trian/core/ui/UIController;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "backStackEntry", "Landroidx/navigation/NavBackStackEntry;", "getBackStackEntry", "()Landroidx/navigation/NavBackStackEntry;", "getController", "()Lapp/trian/core/ui/UIController;", "router", "getRouter", "getState", "()Ljava/lang/Object;", "Ljava/lang/Object;", "addOnBottomSheetStateChangeListener", "listener", "Lapp/trian/core/ui/listener/BottomSheetChangeListener;", "s", "Lkotlin/ExtensionFunctionType;", "dispatch", "e", "(Ljava/lang/Object;)V", "hideBottomSheet", "hideKeyboard", "showBottomSheet", "showSnackbar", "message", "", "params", "", "", "(I[Ljava/lang/String;)V", "core-ui_debug"})
public class UIListener<State extends java.lang.Object, Event extends java.lang.Object> {
    @org.jetbrains.annotations.NotNull
    private final app.trian.core.ui.UIController controller = null;
    private final State state = null;
    private final kotlin.jvm.functions.Function1<State, kotlin.Unit> commit = null;
    private final kotlin.jvm.functions.Function1<Event, kotlin.Unit> dispatcher = null;
    
    public UIListener(@org.jetbrains.annotations.NotNull
    app.trian.core.ui.UIController controller, State state, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super State, kotlin.Unit> commit, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super Event, kotlin.Unit> dispatcher) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final app.trian.core.ui.UIController getController() {
        return null;
    }
    
    public final State getState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final app.trian.core.ui.UIController getRouter() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final androidx.navigation.NavBackStackEntry getBackStackEntry() {
        return null;
    }
    
    public final void commit(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super State, ? extends State> s) {
    }
    
    public final void dispatch(Event e) {
    }
    
    public final void hideBottomSheet() {
    }
    
    public final void showBottomSheet() {
    }
    
    public final void addOnBottomSheetStateChangeListener(@org.jetbrains.annotations.NotNull
    app.trian.core.ui.listener.BottomSheetChangeListener listener) {
    }
    
    public final void hideKeyboard() {
    }
    
    public final void showSnackbar(@org.jetbrains.annotations.NotNull
    java.lang.String message) {
    }
    
    public final void showSnackbar(int message) {
    }
    
    public final void showSnackbar(int message, @org.jetbrains.annotations.NotNull
    java.lang.String... params) {
    }
}