/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.mvi.ui.extensions

fun <T> List<T>.findIndex(key: (IndexedValue<T>) -> Boolean): Int =
    try {
        this.withIndex()
            .first { value -> key(value) }
            .index
    } catch (e: Exception) {
        -1
    }

fun <T> List<T>.add(value: T) =
    this.toMutableList().plus(value)
