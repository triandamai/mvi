package app.trian.core.ui;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u00032\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0004B_\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00028\u0000\u0012\u0006\u0010\b\u001a\u00028\u0001\u0012\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0014\b\u0002\u0010\f\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\u0002\u0010\u000eJ\u001f\u0010\f\u001a\u00020\u000b2\u0017\u0010\u0012\u001a\u0013\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00010\n\u00a2\u0006\u0002\b\u0013R\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0013\u0010\b\u001a\u00028\u0001\u00a2\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0014"}, d2 = {"Lapp/trian/core/ui/UIListenerData;", "State", "Data", "Event", "Lapp/trian/core/ui/UIListener;", "controller", "Lapp/trian/core/ui/UIController;", "state", "data", "commit", "Lkotlin/Function1;", "", "commitData", "dispatcher", "(Lapp/trian/core/ui/UIController;Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "getData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "d", "Lkotlin/ExtensionFunctionType;", "core-ui_debug"})
public final class UIListenerData<State extends java.lang.Object, Data extends java.lang.Object, Event extends java.lang.Object> extends app.trian.core.ui.UIListener<State, Event> {
    private final Data data = null;
    private final kotlin.jvm.functions.Function1<Data, kotlin.Unit> commitData = null;
    
    public UIListenerData(@org.jetbrains.annotations.NotNull
    app.trian.core.ui.UIController controller, State state, Data data, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super State, kotlin.Unit> commit, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super Data, kotlin.Unit> commitData, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super Event, kotlin.Unit> dispatcher) {
        super(null, null, null, null);
    }
    
    public final Data getData() {
        return null;
    }
    
    public final void commitData(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super Data, ? extends Data> d) {
    }
}