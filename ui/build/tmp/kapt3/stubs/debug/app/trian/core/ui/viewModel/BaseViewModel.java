package app.trian.core.ui.viewModel;

import java.lang.System;

@android.annotation.SuppressLint(value = {"StaticFieldLeak"})
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u00a8\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0004\b\'\u0018\u0000 a*\b\b\u0000\u0010\u0001*\u00020\u0002*\u0004\b\u0001\u0010\u00032\u00020\u0004:\u0001aB\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00028\u0000\u00a2\u0006\u0002\u0010\bJ\u000e\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020\nJ\u000e\u00100\u001a\u00020.2\u0006\u0010/\u001a\u00020\u000eJ\u000e\u00101\u001a\u00020.2\u0006\u0010/\u001a\u00020\fJ\u000e\u00102\u001a\u00020.2\u0006\u0010/\u001a\u00020\u0010J\u000e\u00103\u001a\u00020.2\u0006\u0010/\u001a\u00020\u0012J1\u00104\u001a\u0002052\u001e\b\u0004\u00106\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020.08\u0012\u0006\u0012\u0004\u0018\u00010907H\u0084\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010:J1\u0010;\u001a\u0002052\u001e\b\u0004\u00106\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020.08\u0012\u0006\u0012\u0004\u0018\u00010907H\u0084\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010:J<\u0010<\u001a\u0002052)\b\u0004\u00106\u001a#\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020.08\u0012\u0006\u0012\u0004\u0018\u0001090=\u00a2\u0006\u0002\b>H\u0084\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010?J7\u0010@\u001a\u0002HA\"\u0004\b\u0002\u0010A2\u001e\b\u0004\u00106\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002HA08\u0012\u0006\u0012\u0004\u0018\u00010907H\u0084H\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010BJ\u0013\u0010C\u001a\u00020.2\u0006\u0010D\u001a\u00028\u0000\u00a2\u0006\u0002\u0010EJ\u001f\u0010C\u001a\u00020.2\u0017\u0010D\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000007\u00a2\u0006\u0002\b>J\u0013\u0010F\u001a\u0002052\u0006\u0010G\u001a\u00028\u0001\u00a2\u0006\u0002\u0010HJ\b\u0010I\u001a\u00020.H$J\u0006\u0010J\u001a\u00020.J\b\u0010K\u001a\u00020.H\u0014J4\u0010L\u001a\u00020.2\"\u00106\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020.08\u0012\u0006\u0012\u0004\u0018\u0001090=H\u0004\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010MJ\u0006\u0010N\u001a\u00020.J\u0006\u0010O\u001a\u00020.J`\u0010P\u001a\u000205\"\u0006\b\u0002\u0010A\u0018\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002HA0R0Q2\u000e\b\u0006\u0010S\u001a\b\u0012\u0004\u0012\u00020.0T2\u0014\b\u0006\u0010U\u001a\u000e\u0012\u0004\u0012\u00020V\u0012\u0004\u0012\u00020.072\u0014\b\u0006\u0010W\u001a\u000e\u0012\u0004\u0012\u0002HA\u0012\u0004\u0012\u00020.07H\u0084\b\u00f8\u0001\u0001Jp\u0010P\u001a\u000205\"\u0006\b\u0002\u0010A\u0018\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002HA0X0Q2\u000e\b\u0006\u0010S\u001a\b\u0012\u0004\u0012\u00020.0T2\u0014\b\u0006\u0010U\u001a\u000e\u0012\u0004\u0012\u00020V\u0012\u0004\u0012\u00020.072\u0014\b\u0006\u0010W\u001a\u000e\u0012\u0004\u0012\u0002HA\u0012\u0004\u0012\u00020.072\u000e\b\u0006\u0010Y\u001a\b\u0012\u0004\u0012\u00020.0TH\u0084\b\u00f8\u0001\u0001J\u0085\u0001\u0010P\u001a\u000205\"\u0006\b\u0002\u0010A\u0018\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002HA0Z0Q2\u000e\b\u0006\u0010S\u001a\b\u0012\u0004\u0012\u00020.0T2\u0014\b\u0006\u0010U\u001a\u000e\u0012\u0004\u0012\u00020V\u0012\u0004\u0012\u00020.072\u0014\b\u0006\u0010[\u001a\u000e\u0012\u0004\u0012\u0002HA\u0012\u0004\u0012\u00020.072#\b\u0006\u0010\\\u001a\u001d\u0012\u0013\u0012\u00110]\u00a2\u0006\f\b^\u0012\b\b_\u0012\u0004\b\b(`\u0012\u0004\u0012\u00020.07H\u0084\b\u00f8\u0001\u0001R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00010\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\u00020\n8DX\u0084\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0010\u0010\u0007\u001a\u00028\u0000X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u001cR\u0014\u0010\u001d\u001a\u00020\f8DX\u0084\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u0014\u0010 \u001a\u00020\u000e8DX\u0084\u0004\u00a2\u0006\u0006\u001a\u0004\b!\u0010\"R\u0014\u0010#\u001a\u00020\u00108DX\u0084\u0004\u00a2\u0006\u0006\u001a\u0004\b$\u0010%R\u0014\u0010&\u001a\u00020\u00128DX\u0084\u0004\u00a2\u0006\u0006\u001a\u0004\b\'\u0010(R\u0017\u0010)\u001a\b\u0012\u0004\u0012\u00028\u00000*8F\u00a2\u0006\u0006\u001a\u0004\b+\u0010,\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b\u009920\u0001\u00a8\u0006b"}, d2 = {"Lapp/trian/core/ui/viewModel/BaseViewModel;", "State", "Landroid/os/Parcelable;", "Action", "Landroidx/lifecycle/ViewModel;", "context", "Landroid/content/Context;", "initialState", "(Landroid/content/Context;Landroid/os/Parcelable;)V", "_bottomSheetListener", "Lapp/trian/core/ui/listener/BottomSheetListener;", "_keyboardListener", "Lapp/trian/core/ui/listener/KeyboardListener;", "_navigationListener", "Lapp/trian/core/ui/listener/NavigationListener;", "_snackbarListener", "Lapp/trian/core/ui/listener/SnacbarListener;", "_toastListener", "Lapp/trian/core/ui/listener/ToastListener;", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "action", "Lkotlinx/coroutines/channels/Channel;", "bottomSheet", "getBottomSheet", "()Lapp/trian/core/ui/listener/BottomSheetListener;", "getContext", "()Landroid/content/Context;", "Landroid/os/Parcelable;", "keyboard", "getKeyboard", "()Lapp/trian/core/ui/listener/KeyboardListener;", "navigation", "getNavigation", "()Lapp/trian/core/ui/listener/NavigationListener;", "snackbar", "getSnackbar", "()Lapp/trian/core/ui/listener/SnacbarListener;", "toast", "getToast", "()Lapp/trian/core/ui/listener/ToastListener;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "addBottomSheetListener", "", "listener", "addNavigationListener", "addOnKeyboardListener", "addSnackbarListener", "addToastListener", "async", "Lkotlinx/coroutines/Job;", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/Job;", "asyncFlow", "asyncWithState", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/Job;", "await", "T", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "commit", "state", "(Landroid/os/Parcelable;)V", "dispatch", "e", "(Ljava/lang/Object;)Lkotlinx/coroutines/Job;", "handleActions", "hideKeyboard", "onCleared", "onEvent", "(Lkotlin/jvm/functions/Function2;)V", "resetState", "showKeyboard", "onEach", "Lkotlinx/coroutines/flow/Flow;", "Lapp/trian/core/ui/ResultState;", "loading", "Lkotlin/Function0;", "error", "", "success", "Lapp/trian/core/ui/ResultStateData;", "empty", "Lapp/trian/core/ui/ResultStateWithProgress;", "onFinish", "onProgress", "", "Lkotlin/ParameterName;", "name", "progress", "Companion", "core-ui_debug"})
public abstract class BaseViewModel<State extends android.os.Parcelable, Action extends java.lang.Object> extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final android.content.Context context = null;
    private final State initialState = null;
    @org.jetbrains.annotations.NotNull
    public static final app.trian.core.ui.viewModel.BaseViewModel.Companion Companion = null;
    @org.jetbrains.annotations.NotNull
    private static final kotlinx.coroutines.CoroutineDispatcher dispatcher = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<State> _uiState = null;
    private final kotlinx.coroutines.channels.Channel<Action> action = null;
    private app.trian.core.ui.listener.ToastListener _toastListener;
    private app.trian.core.ui.listener.NavigationListener _navigationListener;
    private app.trian.core.ui.listener.BottomSheetListener _bottomSheetListener;
    private app.trian.core.ui.listener.SnacbarListener _snackbarListener;
    private app.trian.core.ui.listener.KeyboardListener _keyboardListener;
    
    public BaseViewModel(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    State initialState) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final android.content.Context getContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<State> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    protected final app.trian.core.ui.listener.ToastListener getToast() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    protected final app.trian.core.ui.listener.NavigationListener getNavigation() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    protected final app.trian.core.ui.listener.BottomSheetListener getBottomSheet() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    protected final app.trian.core.ui.listener.SnacbarListener getSnackbar() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    protected final app.trian.core.ui.listener.KeyboardListener getKeyboard() {
        return null;
    }
    
    public final void addToastListener(@org.jetbrains.annotations.NotNull
    app.trian.core.ui.listener.ToastListener listener) {
    }
    
    public final void addNavigationListener(@org.jetbrains.annotations.NotNull
    app.trian.core.ui.listener.NavigationListener listener) {
    }
    
    public final void addBottomSheetListener(@org.jetbrains.annotations.NotNull
    app.trian.core.ui.listener.BottomSheetListener listener) {
    }
    
    public final void addSnackbarListener(@org.jetbrains.annotations.NotNull
    app.trian.core.ui.listener.SnacbarListener listener) {
    }
    
    public final void addOnKeyboardListener(@org.jetbrains.annotations.NotNull
    app.trian.core.ui.listener.KeyboardListener listener) {
    }
    
    public final void showKeyboard() {
    }
    
    public final void hideKeyboard() {
    }
    
    protected final void onEvent(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function2<? super Action, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> block) {
    }
    
    @org.jetbrains.annotations.NotNull
    protected final kotlinx.coroutines.Job async(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> block) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    protected final kotlinx.coroutines.Job asyncWithState(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function2<? super State, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> block) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    protected final <T extends java.lang.Object>java.lang.Object await(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super T>, ? extends java.lang.Object> block, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super T> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    protected final kotlinx.coroutines.Job asyncFlow(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> block) {
        return null;
    }
    
    protected abstract void handleActions();
    
    public final void commit(@org.jetbrains.annotations.NotNull
    State state) {
    }
    
    public final void commit(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super State, ? extends State> state) {
    }
    
    public final void resetState() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.Job dispatch(Action e) {
        return null;
    }
    
    @java.lang.Override
    protected void onCleared() {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lapp/trian/core/ui/viewModel/BaseViewModel$Companion;", "", "()V", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "getDispatcher", "()Lkotlinx/coroutines/CoroutineDispatcher;", "core-ui_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final kotlinx.coroutines.CoroutineDispatcher getDispatcher() {
            return null;
        }
    }
}