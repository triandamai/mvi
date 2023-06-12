/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.resepku.feature.recipe.detailRecipe

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat

@Composable
fun ScreenDetailRecipe() {
    val view = LocalView.current
    (view.context as? Activity)?.window?.let {
        WindowCompat.setDecorFitsSystemWindows(it,true)
    }
}

@Preview
@Composable
fun PreviewScreenDetailRecipe() {

}