package app.trian.core.ui;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u000e\u0010\'\u001a\u00020(2\u0006\u0010)\u001a\u00020*J\u000e\u0010+\u001a\u00020(2\u0006\u0010)\u001a\u00020,J\u0006\u0010-\u001a\u00020(J\u000e\u0010.\u001a\u00020\u00122\u0006\u0010/\u001a\u000200J\'\u0010.\u001a\u00020\u00122\u0006\u0010/\u001a\u0002002\u0012\u00101\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001202\"\u00020\u0012\u00a2\u0006\u0002\u00103J\u0006\u00104\u001a\u00020(J\u0006\u00105\u001a\u00020(J\'\u00106\u001a\u00020(2\u0006\u00107\u001a\u00020\u00122\u0012\u00108\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001202\"\u00020\u0012\u00a2\u0006\u0002\u00109J\'\u0010:\u001a\u00020(2\u0006\u00107\u001a\u00020\u00122\u0012\u00108\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001202\"\u00020\u0012\u00a2\u0006\u0002\u00109J\'\u0010;\u001a\u00020(2\u0006\u00107\u001a\u00020\u00122\u0012\u00108\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001202\"\u00020\u0012\u00a2\u0006\u0002\u00109J\u0006\u0010<\u001a\u00020(J\u000e\u0010=\u001a\u00020(2\u0006\u00107\u001a\u00020\u0012J\'\u0010=\u001a\u00020(2\u0006\u00107\u001a\u00020\u00122\u0012\u00108\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001202\"\u00020\u0012\u00a2\u0006\u0002\u00109J\u0006\u0010>\u001a\u00020(J.\u0010?\u001a\u00020@2\u001e\b\u0002\u0010A\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020(0C\u0012\u0006\u0012\u0004\u0018\u00010\u00010B\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010DJ\u000e\u0010E\u001a\u00020(2\u0006\u0010F\u001a\u00020\u0012J\u0006\u0010G\u001a\u00020(J\u000e\u0010H\u001a\u00020(2\u0006\u0010I\u001a\u000200J\'\u0010H\u001a\u00020(2\u0006\u0010I\u001a\u0002002\u0012\u00101\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001202\"\u00020\u0012\u00a2\u0006\u0002\u0010JJ\u000e\u0010H\u001a\u00020(2\u0006\u0010I\u001a\u00020\u0012J\u0016\u0010H\u001a\u00020(2\u0006\u0010I\u001a\u00020\u00122\u0006\u0010K\u001a\u00020LJ&\u0010H\u001a\u00020(2\u0006\u0010I\u001a\u00020\u00122\u0006\u0010M\u001a\u00020\u00122\u000e\b\u0002\u0010N\u001a\b\u0012\u0004\u0012\u00020(0OR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R+\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u00128F@FX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR+\u0010!\u001a\u00020 2\u0006\u0010\u0011\u001a\u00020 8F@FX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b&\u0010\u0019\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006P"}, d2 = {"Lapp/trian/core/ui/UIController;", "", "router", "Landroidx/navigation/NavHostController;", "bottomSheetState", "Landroidx/compose/material/ModalBottomSheetState;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "event", "Lapp/trian/core/ui/listener/BaseEventListener;", "context", "Landroid/content/Context;", "(Landroidx/navigation/NavHostController;Landroidx/compose/material/ModalBottomSheetState;Lkotlinx/coroutines/CoroutineScope;Lapp/trian/core/ui/listener/BaseEventListener;Landroid/content/Context;)V", "getBottomSheetState", "()Landroidx/compose/material/ModalBottomSheetState;", "getContext", "()Landroid/content/Context;", "<set-?>", "", "currentRoute", "getCurrentRoute", "()Ljava/lang/String;", "setCurrentRoute", "(Ljava/lang/String;)V", "currentRoute$delegate", "Landroidx/compose/runtime/MutableState;", "getEvent", "()Lapp/trian/core/ui/listener/BaseEventListener;", "getRouter", "()Landroidx/navigation/NavHostController;", "getScope", "()Lkotlinx/coroutines/CoroutineScope;", "Landroidx/compose/material3/SnackbarHostState;", "snackbarHostState", "getSnackbarHostState", "()Landroidx/compose/material3/SnackbarHostState;", "setSnackbarHostState", "(Landroidx/compose/material3/SnackbarHostState;)V", "snackbarHostState$delegate", "addOnBottomSheetStateChangeListener", "", "listener", "Lapp/trian/core/ui/listener/BottomSheetChangeListener;", "addOnEventListener", "Lapp/trian/core/ui/listener/AppStateEventListener;", "exit", "getString", "res", "", "params", "", "(I[Ljava/lang/String;)Ljava/lang/String;", "hideBottomSheet", "listenChanges", "navigate", "routeName", "args", "(Ljava/lang/String;[Ljava/lang/String;)V", "navigateAndReplace", "navigateAndReplaceAll", "navigateBackAndClose", "navigateSingleTop", "navigateUp", "runSuspend", "Lkotlinx/coroutines/Job;", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "(Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/Job;", "sendEvent", "eventName", "showBottomSheet", "showSnackbar", "message", "(I[Ljava/lang/String;)V", "duration", "Landroidx/compose/material3/SnackbarDuration;", "actionLabel", "onAction", "Lkotlin/Function0;", "core-ui_debug"})
public final class UIController {
    @org.jetbrains.annotations.NotNull
    private final androidx.navigation.NavHostController router = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.compose.material.ModalBottomSheetState bottomSheetState = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.CoroutineScope scope = null;
    @org.jetbrains.annotations.NotNull
    private final app.trian.core.ui.listener.BaseEventListener event = null;
    @org.jetbrains.annotations.NotNull
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.compose.runtime.MutableState currentRoute$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.compose.runtime.MutableState snackbarHostState$delegate = null;
    
    public UIController(@org.jetbrains.annotations.NotNull
    androidx.navigation.NavHostController router, @org.jetbrains.annotations.NotNull
    androidx.compose.material.ModalBottomSheetState bottomSheetState, @org.jetbrains.annotations.NotNull
    kotlinx.coroutines.CoroutineScope scope, @org.jetbrains.annotations.NotNull
    app.trian.core.ui.listener.BaseEventListener event, @org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.navigation.NavHostController getRouter() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.compose.material.ModalBottomSheetState getBottomSheetState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.CoroutineScope getScope() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final app.trian.core.ui.listener.BaseEventListener getEvent() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final android.content.Context getContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getCurrentRoute() {
        return null;
    }
    
    public final void setCurrentRoute(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.compose.material3.SnackbarHostState getSnackbarHostState() {
        return null;
    }
    
    public final void setSnackbarHostState(@org.jetbrains.annotations.NotNull
    androidx.compose.material3.SnackbarHostState p0) {
    }
    
    public final void showSnackbar(@org.jetbrains.annotations.NotNull
    java.lang.String message) {
    }
    
    public final void showSnackbar(int message) {
    }
    
    public final void showSnackbar(int message, @org.jetbrains.annotations.NotNull
    java.lang.String... params) {
    }
    
    public final void showSnackbar(@org.jetbrains.annotations.NotNull
    java.lang.String message, @org.jetbrains.annotations.NotNull
    androidx.compose.material3.SnackbarDuration duration) {
    }
    
    public final void showSnackbar(@org.jetbrains.annotations.NotNull
    java.lang.String message, @org.jetbrains.annotations.NotNull
    java.lang.String actionLabel, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onAction) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.Job runSuspend(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> block) {
        return null;
    }
    
    public final void navigateBackAndClose() {
    }
    
    public final void navigateUp() {
    }
    
    /**
     * Navigation into [routeName] as destination and keep the previous route
     *
     * @param routeName is a destination
     * @param args is arguments/parameter also support multiple type
     *
     * @throws IllegalArgumentException from navHostController
     */
    public final void navigate(@org.jetbrains.annotations.NotNull
    java.lang.String routeName, @org.jetbrains.annotations.NotNull
    java.lang.String... args) {
    }
    
    /**
     * Navigation into [routeName] as destination and keep the previous route
     * if same route exist on back stack, will be replace with [routeName]
     *
     * @param routeName is a destination
     * @param args is arguments/parameter also support multiple type
     *
     * @throws IllegalArgumentException from navHostController
     */
    public final void navigateSingleTop(@org.jetbrains.annotations.NotNull
    java.lang.String routeName, @org.jetbrains.annotations.NotNull
    java.lang.String... args) {
    }
    
    public final void navigateSingleTop(@org.jetbrains.annotations.NotNull
    java.lang.String routeName) {
    }
    
    /**
     * Navigation into [routeName] as destination, and pop all backstack before last route
     * if same route exist on back stack, will be replace with [routeName]
     *
     * @param routeName is a destination
     * @param args is arguments/parameter also support multiple type
     *
     * @throws IllegalArgumentException from navHostController
     */
    public final void navigateAndReplace(@org.jetbrains.annotations.NotNull
    java.lang.String routeName, @org.jetbrains.annotations.NotNull
    java.lang.String... args) {
    }
    
    /**
     * Navigation into [routeName] as destination, and pop all backstack from last route
     * if same route exist on back stack, will be replace with [routeName]
     *
     * @param routeName is a destination
     * @param args is arguments/parameter also support multiple type
     *
     *
     * @throws IllegalArgumentException from navHostController
     */
    public final void navigateAndReplaceAll(@org.jetbrains.annotations.NotNull
    java.lang.String routeName, @org.jetbrains.annotations.NotNull
    java.lang.String... args) {
    }
    
    public final void addOnEventListener(@org.jetbrains.annotations.NotNull
    app.trian.core.ui.listener.AppStateEventListener listener) {
    }
    
    public final void addOnBottomSheetStateChangeListener(@org.jetbrains.annotations.NotNull
    app.trian.core.ui.listener.BottomSheetChangeListener listener) {
    }
    
    public final void sendEvent(@org.jetbrains.annotations.NotNull
    java.lang.String eventName) {
    }
    
    public final void exit() {
    }
    
    public final void listenChanges() {
    }
    
    public final void showBottomSheet() {
    }
    
    public final void hideBottomSheet() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getString(int res) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getString(int res, @org.jetbrains.annotations.NotNull
    java.lang.String... params) {
        return null;
    }
}