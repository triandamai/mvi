/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.resepku.feature.recipe.createRecipe.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.core.ui.BaseMainApp
import app.trian.core.ui.component.ButtonIcon
import app.trian.core.ui.component.ItemCookingStep
import app.trian.resepku.feature.recipe.createRecipe.CookingStep
import org.burnoutcrew.reorderable.ReorderableItem
import org.burnoutcrew.reorderable.detectReorderAfterLongPress
import org.burnoutcrew.reorderable.rememberReorderableLazyListState
import org.burnoutcrew.reorderable.reorderable

@Composable
fun ScreenInputStep(
    dataCookingStep: List<CookingStep> = listOf(),
    onMove: (from: Int, to: Int) -> Unit = { _, _ -> },
    onAddCookingStep: () -> Unit = {},
    onChangeCookingStep: (data: CookingStep) -> Unit = {},
    onRemoveCookingStep: (data: CookingStep) -> Unit = {},
) {
    val reorder = rememberReorderableLazyListState(onMove = { from, to ->
        onMove(from.index, to.index)
    })
    LazyColumn(
        state = reorder.listState,
        modifier = Modifier
            .fillMaxSize()
            .reorderable(reorder)
            .detectReorderAfterLongPress(reorder),
        content = {
            if (dataCookingStep.isEmpty()) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                vertical = 16.dp,
                                horizontal = 16.dp
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Click button + below to add new Step!",
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            itemsIndexed(dataCookingStep, key = { _, item -> item.id }) { index, item ->
                ReorderableItem(
                    reorderableState = reorder,
                    key = item.id
                ) { isDragging ->
                    ItemCookingStep(
                        isDragging = isDragging,
                        number="${(index+1)}",
                        value = item.value,
                        onChange = { value ->
                            onChangeCookingStep(
                                CookingStep(
                                    value = value,
                                    id = item.id
                                )
                            )
                        },
                        onRemove = {
                            onRemoveCookingStep(item)
                        }
                    )
                }
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(

                        )
                        .padding(
                            horizontal = 16.dp,
                            vertical = 10.dp
                        ),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ButtonIcon(
                        icon = Icons.Outlined.Add,
                        text = "Add Step",
                        fullWidth = false,
                        onClick = onAddCookingStep,
                        modifier = Modifier.fillMaxWidth(
                            fraction = 0.6f
                        )
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    )
}

@Preview
@Composable
fun PreviewScreenInputStep() {
    BaseMainApp {
        ScreenInputStep()
    }
}