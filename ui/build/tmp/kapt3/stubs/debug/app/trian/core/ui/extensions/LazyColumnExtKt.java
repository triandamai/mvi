package app.trian.core.ui.extensions;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 2, d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\u001aH\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00072\"\u0010\b\u001a\u001e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\t\u00a2\u0006\u0002\b\u000b\u00a2\u0006\u0002\b\f\u001aP\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000e2\"\u0010\b\u001a\u001e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\t\u00a2\u0006\u0002\b\u000b\u00a2\u0006\u0002\b\f\u001aT\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u000f*\u00020\u00022\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u00112\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00072\"\u0010\b\u001a\u001e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u00020\u00010\t\u00a2\u0006\u0002\b\u000b\u00a2\u0006\u0002\b\f\u001a\\\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u000f*\u00020\u00022\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u00112\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000e2\"\u0010\b\u001a\u001e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u00020\u00010\t\u00a2\u0006\u0002\b\u000b\u00a2\u0006\u0002\b\f\u00a8\u0006\u0012"}, d2 = {"gridItems", "", "Landroidx/compose/foundation/lazy/LazyListScope;", "count", "", "columnCount", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "itemContent", "Lkotlin/Function2;", "Landroidx/compose/foundation/layout/BoxScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "paddingValues", "Landroidx/compose/foundation/layout/PaddingValues;", "T", "data", "", "core-ui_debug"})
public final class LazyColumnExtKt {
    
    public static final void gridItems(@org.jetbrains.annotations.NotNull
    androidx.compose.foundation.lazy.LazyListScope $this$gridItems, int count, int columnCount, @org.jetbrains.annotations.NotNull
    androidx.compose.foundation.layout.Arrangement.Horizontal horizontalArrangement, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function2<? super androidx.compose.foundation.layout.BoxScope, ? super java.lang.Integer, kotlin.Unit> itemContent) {
    }
    
    public static final <T extends java.lang.Object>void gridItems(@org.jetbrains.annotations.NotNull
    androidx.compose.foundation.lazy.LazyListScope $this$gridItems, @org.jetbrains.annotations.NotNull
    java.util.List<? extends T> data, int columnCount, @org.jetbrains.annotations.NotNull
    androidx.compose.foundation.layout.Arrangement.Horizontal horizontalArrangement, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function2<? super androidx.compose.foundation.layout.BoxScope, ? super T, kotlin.Unit> itemContent) {
    }
    
    public static final void gridItems(@org.jetbrains.annotations.NotNull
    androidx.compose.foundation.lazy.LazyListScope $this$gridItems, int count, int columnCount, @org.jetbrains.annotations.NotNull
    androidx.compose.foundation.layout.Arrangement.Horizontal horizontalArrangement, @org.jetbrains.annotations.NotNull
    androidx.compose.foundation.layout.PaddingValues paddingValues, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function2<? super androidx.compose.foundation.layout.BoxScope, ? super java.lang.Integer, kotlin.Unit> itemContent) {
    }
    
    public static final <T extends java.lang.Object>void gridItems(@org.jetbrains.annotations.NotNull
    androidx.compose.foundation.lazy.LazyListScope $this$gridItems, @org.jetbrains.annotations.NotNull
    java.util.List<? extends T> data, int columnCount, @org.jetbrains.annotations.NotNull
    androidx.compose.foundation.layout.Arrangement.Horizontal horizontalArrangement, @org.jetbrains.annotations.NotNull
    androidx.compose.foundation.layout.PaddingValues paddingValues, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function2<? super androidx.compose.foundation.layout.BoxScope, ? super T, kotlin.Unit> itemContent) {
    }
}