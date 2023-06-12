/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.resepku.feature.recipe.createRecipe

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.core.ui.BaseMainApp
import app.trian.core.ui.BaseScreen
import app.trian.core.ui.UIListenerData
import app.trian.core.ui.UIWrapper
import app.trian.core.ui.component.ButtonPrimary
import app.trian.core.ui.component.ButtonSecondary
import app.trian.core.ui.component.HeaderStepWithProgress
import app.trian.core.ui.extensions.coloredShadow
import app.trian.resepku.feature.recipe.createRecipe.components.ScreenInputIngredient
import app.trian.resepku.feature.recipe.createRecipe.components.ScreenInputStep
import app.trian.resepku.feature.recipe.createRecipe.components.ScreenMain
import app.trian.resepku.feature.recipe.createRecipe.components.ScreenSuccessCreateRecipe

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun ScreenCreateRecipe(
    uiEvent: UIListenerData<CreateRecipeState, CreateRecipeDataState, CreateRecipeEvent>
) = UIWrapper(uiEvent) {

    BackHandler {
        dispatch(CreateRecipeEvent.ChangeStep(isNextStep = false))
    }

    BaseScreen(
        controller = uiEvent.controller,
        bottomSheet = {

        },
        bottomBar = {
            AnimatedVisibility(
                visible = state.visibleBottomBar,
                enter = slideInVertically(initialOffsetY = { it / 2 }),
                exit = slideOutVertically(targetOffsetY = { it / 2 })
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .coloredShadow(
                            color = MaterialTheme.colorScheme.primary,
                            alpha = 0.2f,
                            borderRadius = 20.dp
                        )
                        .clip(
                            RoundedCornerShape(
                                topStart = 16.dp,
                                topEnd = 16.dp
                            )
                        )
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(
                            vertical = 16.dp
                        ),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ButtonSecondary(
                        text = "Kembali",
                        modifier = Modifier
                            .defaultMinSize(
                                minWidth = 150.dp
                            ),
                        fullWidth = false,
                        onClick = {
                            dispatch(CreateRecipeEvent.ChangeStep(isNextStep = false))
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    ButtonPrimary(
                        text = "Lanjut",
                        modifier = Modifier
                            .defaultMinSize(
                                minWidth = 150.dp
                            ),
                        fullWidth = false,
                        enabled = true,
                        onClick = {
                            dispatch(CreateRecipeEvent.ChangeStep(isNextStep = true))
                        }
                    )

                }
            }
        },
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            if (state.step != 3) {
                HeaderStepWithProgress(
                    progress = (state.step + 1),
                    total = 3,
                    onClose = {
                        showBottomSheet()
                    },
                    onBackPress = {
                        dispatch(CreateRecipeEvent.ChangeStep(isNextStep = false))
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            AnimatedContent(
                targetState = state.step,
                transitionSpec = {
                    fadeIn() with fadeOut()
                },
                label = ""
            ) {
                when (state.step) {
                    0 -> ScreenMain()
                    1 -> ScreenInputIngredient(
                        dataIngredient = state.dataIngredient,
                        onMove = { from, to ->
                            dispatch(CreateRecipeEvent.ReorderIngredient(from = from, to = to))
                        },
                        onChangeIngredient = {
                            dispatch(CreateRecipeEvent.ChangeIngredient(it))
                        },
                        onAddIngredient = {
                            dispatch(CreateRecipeEvent.AddNewIngredient)
                        },
                        onRemoveIngredient = {
                            dispatch(CreateRecipeEvent.RemoveIngredient(it.id))
                        }
                    )

                    2 -> ScreenInputStep(
                        dataCookingStep = state.dataCookingStep,
                        onMove = { from, to ->
                            dispatch(CreateRecipeEvent.ReorderCookingStep(from = from, to = to))
                        },
                        onChangeCookingStep = {
                            dispatch(CreateRecipeEvent.ChangeCookingStep(it))
                        },
                        onAddCookingStep = {
                            dispatch(CreateRecipeEvent.AddNewCookingStep)
                        },
                        onRemoveCookingStep = {
                            dispatch(CreateRecipeEvent.RemoveCookingStep(it.id))
                        }
                    )

                    3 -> ScreenSuccessCreateRecipe(
                        onClose = { navigateUp() },
                        onEdit = {}
                    )
                    else -> Unit
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewScreenCreateRecipe() {

    var state by remember {
        mutableStateOf(CreateRecipeState())
    }

    var data by remember {
        mutableStateOf(CreateRecipeDataState())
    }
    BaseMainApp {
        ScreenCreateRecipe(
            uiEvent = UIListenerData(
                controller = it,
                state = state,
                data = data,
                commit = {
                    state = it
                },
                commitData = {
                    data = it
                },
                dispatcher = {
                    when (it) {
                        CreateRecipeEvent.AddNewCookingStep -> TODO()
                        CreateRecipeEvent.AddNewIngredient -> TODO()
                        is CreateRecipeEvent.ChangeCookingStep -> TODO()
                        is CreateRecipeEvent.ChangeIngredient -> TODO()
                        is CreateRecipeEvent.ChangeStep -> {
                            val step = if (it.isNextStep) state.step + 1
                            else state.step - 1

                            state = state.copy(step = step)
                        }

                        is CreateRecipeEvent.RemoveCookingStep -> TODO()
                        is CreateRecipeEvent.RemoveIngredient -> TODO()
                        is CreateRecipeEvent.ReorderCookingStep -> TODO()
                        is CreateRecipeEvent.ReorderIngredient -> TODO()
                    }
                }
            )
        )
    }
}