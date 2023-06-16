package app.trian.core.ui.viewModel;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\'\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u0002*\u0004\b\u0002\u0010\u00042\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00040\u0005B\u001d\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00028\u0000\u0012\u0006\u0010\t\u001a\u00028\u0001\u00a2\u0006\u0002\u0010\nJ<\u0010\u0012\u001a\u00020\u00132)\b\u0004\u0010\u0014\u001a#\b\u0001\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00180\u0015\u00a2\u0006\u0002\b\u0019H\u0084\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001aJ\u0013\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00028\u0001\u00a2\u0006\u0002\u0010\u001dJ\u001f\u0010\u001b\u001a\u00020\u00172\u0017\u0010\u001c\u001a\u0013\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00010\u001e\u00a2\u0006\u0002\b\u0019J\b\u0010\u001f\u001a\u00020\u0017H\u0014J\u0006\u0010 \u001a\u00020\u0017R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00010\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00028\u0001X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\rR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00010\u000f8F\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006!"}, d2 = {"Lapp/trian/core/ui/viewModel/BaseViewModelData;", "State", "Landroid/os/Parcelable;", "DataState", "Action", "Lapp/trian/core/ui/viewModel/BaseViewModel;", "context", "Landroid/content/Context;", "initialState", "initialData", "(Landroid/content/Context;Landroid/os/Parcelable;Landroid/os/Parcelable;)V", "_uiDataState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Landroid/os/Parcelable;", "uiDataState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiDataState", "()Lkotlinx/coroutines/flow/StateFlow;", "asyncWithData", "Lkotlinx/coroutines/Job;", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/Job;", "commitData", "dataState", "(Landroid/os/Parcelable;)V", "Lkotlin/Function1;", "onCleared", "resetDataState", "core-ui_debug"})
public abstract class BaseViewModelData<State extends android.os.Parcelable, DataState extends android.os.Parcelable, Action extends java.lang.Object> extends app.trian.core.ui.viewModel.BaseViewModel<State, Action> {
    private final DataState initialData = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<DataState> _uiDataState = null;
    
    public BaseViewModelData(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    State initialState, @org.jetbrains.annotations.NotNull
    DataState initialData) {
        super(null, null);
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<DataState> getUiDataState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    protected final kotlinx.coroutines.Job asyncWithData(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function2<? super DataState, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> block) {
        return null;
    }
    
    public final void commitData(@org.jetbrains.annotations.NotNull
    DataState dataState) {
    }
    
    public final void commitData(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super DataState, ? extends DataState> dataState) {
    }
    
    public final void resetDataState() {
    }
    
    @java.lang.Override
    protected void onCleared() {
    }
}